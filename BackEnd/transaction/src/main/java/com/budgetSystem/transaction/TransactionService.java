package com.budgetSystem.transaction;

import com.budgetSystem.transaction.clients.BudgetClient;
import com.budgetSystem.transaction.clients.WalletClient;
import com.budgetSystem.transaction.dto.CreateTransactionReq;
import com.budgetSystem.transaction.dto.TransactionFilterRequest;
import com.budgetSystem.transaction.dto.TransactionResponse;
import com.budgetSystem.transaction.model.TransactionType;
import com.sama.budget.BudgetResponse;
import com.sama.budget.UpdateBudgetRequest;
import com.sama.exceptions.ExceedingAmountException;
import com.sama.exceptions.InsufficientBalanceException;
import com.sama.exceptions.RecordNotFoundException;
import com.budgetSystem.transaction.model.Transaction;
import com.sama.wallet.UpdateWalletRequest;
import com.sama.wallet.WalletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
@Service
@RequiredArgsConstructor
public class TransactionService {
    private final TransactionRepository repository;
    private final WalletClient walletClient;
    private final BudgetClient budgetClient;

    //to-do: refactor to event-driven
    public TransactionResponse saveTransaction(CreateTransactionReq dto)
    {
        Transaction transaction = TransactionMapper.convertToEntity(dto);
        WalletResponse walletResponse = walletClient.findWallet(transaction.getWalletId()).getBody();
        BigDecimal walletBalance = walletResponse.getBalance();

        if(transaction.getType()== TransactionType.WITHDRAW) {
            if(transaction.getAmount().compareTo(walletBalance) > 0) {
                throw new InsufficientBalanceException("Transaction amount for the withdraw cannot be greater than the wallet balance");
            }
            else {
                BudgetResponse budgetResponse = budgetClient.getBudget(transaction.getBudgetId()).getBody();
                BigDecimal budgetRemainingAmount = budgetResponse.getRemainingAmount();
                if(transaction.getAmount().compareTo(budgetRemainingAmount)>0) {
                    throw new ExceedingAmountException("Transaction Amount is more than the required amount for the budget needed money");
                }
                else {
                    UpdateBudgetRequest budgetRequest = UpdateBudgetRequest.builder()
                            .budgetId(transaction.getBudgetId())
                            .remainingAmount(budgetRemainingAmount.subtract(transaction.getAmount()))
                            .build();
                    budgetClient.updateBudget(budgetRequest);
                    walletBalance = walletBalance.subtract(transaction.getAmount());
                }
            }
        }
        else {
            walletBalance = walletBalance.add(transaction.getAmount());
        }
        UpdateWalletRequest request = UpdateWalletRequest.builder()
            .id(walletResponse.getId())
            .balance(walletBalance)
            .build();
        walletClient.updateWallet(request);

        repository.save(transaction);
        return TransactionMapper.convertToResponce(transaction);
    }

    public List<TransactionResponse> findAllTransactionsByClient(Integer clientId) {
        List<Transaction> transactions = repository.findAllByClientId(clientId);
        if (transactions.isEmpty()) throw new RecordNotFoundException("No transactions yet by the client");
        return transactions.stream()
                .map(TransactionMapper::convertToResponce)
                .toList();
    }

    public Integer findTotalNumberOfAllTransactionsByClient(Integer clientID) {
        List<Transaction> transactions = repository.findAllByClientId(clientID);
        return transactions.size();
    }

    public TransactionResponse findTransaction(Integer TransactionID) {
        Transaction transaction = repository.findById(TransactionID)
                .orElseThrow(()-> new RecordNotFoundException("Transaction not found"));
        return TransactionMapper.convertToResponce(transaction);
    }

    public List<TransactionResponse> filterTransactions(TransactionFilterRequest dto) {
        LocalDate startDate = dto.getStartDate()==null? LocalDate.of(2025, 1, 1) : dto.getStartDate() ;
        LocalDate endDate = dto.getEndDate()==null?  LocalDate.now() : dto.getEndDate();
        Integer clientId = dto.getClientId();
        List<String> walletTypes = dto.getWalletTypes();

        List<WalletResponse> clientWallets = walletClient.findAllWalletsByClient(clientId).getBody();

        List<String> filterdTypes = walletTypes.isEmpty() || walletTypes==null
                ? clientWallets.stream()
                .map(walletResponse -> walletResponse.getType().toLowerCase())
                .toList()
                : walletTypes.stream()
                .map(String::toLowerCase)
                .toList();

        List<Integer> walletsId = clientWallets.stream()
                .filter(w -> filterdTypes.contains(w))
                .map(WalletResponse::getId)
                .toList();

        List<Transaction> filteredTransactions = repository.findByClientIdAndWalletIdInAndTransactionDateBetween(clientId,walletsId,startDate,endDate);
        if (filteredTransactions.isEmpty()) throw new RecordNotFoundException("No transactions found within the filter search");
        return filteredTransactions.stream()
                .map(TransactionMapper::convertToResponce)
                .toList();
    }

}