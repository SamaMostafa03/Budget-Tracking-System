package com.budgetSystem.transaction;

import com.budgetSystem.transaction.exceptions.RecordNotFoundExecption;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TransactionService {
    @Autowired
    private final TransactionRepository repository;

    public void saveTransaction(Transaction transaction)
    {
        if(transaction.getInflowAmount()+transaction.getOutflowAmount()==0)
        {
            throw new RuntimeException("Inflow and Outflow amounts cannot be both zero");
        }
        Double inflowForWallet = calculateWalletInflowForUser(transaction.getClientID(),transaction.getWalletType());
        if(transaction.getOutflowAmount()>inflowForWallet)
        {
            throw new RuntimeException("outflow amount cannot be greater than the total wallet inflow");
        }
        repository.save(transaction);
    }

    public List<Transaction> findAllTransactions()
    {
        List<Transaction> transactions = repository.findAll();
        if(transactions.size()==0)throw new RecordNotFoundExecption("No transactions on the system yet");
        return transactions;
    }

    public List<Transaction> findAllTransactionsByClient(Integer clientID) {
        List<Transaction> transactions = repository.findAllByClientID(clientID);
        if (transactions.size() == 0) throw new RecordNotFoundExecption("No transactions yet by the client");
        return transactions;
    }

    public Integer findNumberOfAllTransactionsByClient(Integer clientID) {
        List<Transaction> transactions = repository.findAllByClientID(clientID);
        return transactions.size();
    }

    public void deleteTransaction(Integer transactionID) {
        Optional<Transaction> transactionOptional = repository.findById(transactionID);
        if (transactionOptional.isPresent()) {
            repository.deleteById(transactionID);
        } else {
            throw new RecordNotFoundExecption("Transaction ID not found");
        }
    }

    public void updateTransaction(Transaction transactionObject) {
      saveTransaction(transactionObject);
    }


    public Transaction findTransaction(Integer clientID ,Integer TransactionID) {
        Optional<Transaction> result = repository.findById(TransactionID);
        if (result.isPresent())
        {
            if (!result.get().getClientID().equals(clientID))
            {
                throw new RecordNotFoundExecption("client has no such transaction id");
            }
            return result.get();
        }
        throw new RecordNotFoundExecption("Transaction not found");
    }

    public List<Transaction> filterTransactionsOfClient(Integer clientId, LocalDate startDate, LocalDate endDate,
                                                        List<String> walletTypes) {
        if(walletTypes!=null)
        {
            for (String walletType : walletTypes) {
                if(walletType == null || !walletType.matches("^(cash|visa|bank|debit)$"))
                {
                    throw new RecordNotFoundExecption("Wallet type must be cash, visa, bank, or debit");
                };
            }
        }
        List<Transaction> clientTransactions = repository.findAllByClientID(clientId);
        List<Transaction> filteredTransactions = clientTransactions.stream()
                .filter(transaction ->
                        transaction.getTransactionDate().compareTo(startDate) >= 0 &&
                                transaction.getTransactionDate().compareTo(endDate) <= 0 &&
                                (Objects.isNull(walletTypes) || walletTypes.isEmpty() ||
                                        walletTypes.contains(transaction.getWalletType())))
                .collect(Collectors.toList());
        if (filteredTransactions.size() >= 1) return filteredTransactions;
        throw new RecordNotFoundExecption("No transactions found within the filter search");
    }


    public List<Transaction> CheckForTransactionsOfClientByWallet(Integer clientId, String walletType)
    {
        List<Transaction> filteredTransactions = filterTransactionsOfClientByWallet(clientId,walletType);
        if (filteredTransactions==null) throw new RecordNotFoundExecption("No transactions found within the wallet type");
        return filteredTransactions;
    }

    private List<Transaction> filterTransactionsOfClientByWallet(Integer clientId, String walletType)
    {
        if (walletType == null || !walletType.matches("^(cash|visa|bank|debit)$")) {
            throw new RecordNotFoundExecption("Wallet type must be cash, visa, bank, or debit");
        }
        List<Transaction> clientTransactions = repository.findAllByClientID(clientId);
        List<Transaction> filteredTransactions = clientTransactions.stream()
                .filter(transaction -> transaction.getWalletType().equals(walletType))
                .collect(Collectors.toList());
        return filteredTransactions;
    }



    public double calculateOutflowForUser(int targetId)
    {
        List<Transaction> transactions = repository.findByTargetID(targetId);
        double totalOutflow = 0.0;
        for (Transaction transaction : transactions) {
            totalOutflow += transaction.getOutflowAmount();
        }
        return totalOutflow;
    }

    public Double calculateWalletInflowForUser(int clientId,String walletType)
    {
        List<Transaction> transactions = filterTransactionsOfClientByWallet(clientId,walletType);
        double walletTotalInflow = 0.0;
        if(transactions!=null)
        {
            for (Transaction transaction : transactions) {
                walletTotalInflow += transaction.getInflowAmount();
            }
            for (Transaction transaction : transactions) {
                walletTotalInflow -= transaction.getOutflowAmount();
            }
        }
        return walletTotalInflow;
    }

    public Double calculateTotalInflowForUser(int clientId)
    {
        Double totalInflow = 0.0;
        totalInflow += calculateWalletInflowForUser(clientId,"cash");
        totalInflow += calculateWalletInflowForUser(clientId,"visa");
        totalInflow += calculateWalletInflowForUser(clientId,"bank");
        totalInflow += calculateWalletInflowForUser(clientId,"debit");
        return totalInflow;
    }
}