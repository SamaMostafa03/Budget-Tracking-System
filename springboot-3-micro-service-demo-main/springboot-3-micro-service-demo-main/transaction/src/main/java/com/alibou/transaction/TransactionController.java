package com.alibou.transaction;

import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/transaction")
@RequiredArgsConstructor
public class TransactionController {
    private final TransactionService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void saveTransaction(@RequestBody Transaction transaction)
    {
        service.saveTransaction(transaction);
    }

    @GetMapping
    public ResponseEntity<List<Transaction>> findAllTransactions()
    {
        return ResponseEntity.ok(service.findAllTransactions());
    }

    @GetMapping("client-id/{client-id}")
    public ResponseEntity<List<Transaction>> findAllTransactionsByClient(@PathVariable("client-id") Integer clientID)
    {
        return ResponseEntity.ok(service.findAllTransactionsByClient(clientID));
    }

    @GetMapping("client-id/{client-id}/transactions-number")
    public ResponseEntity<Integer> findNumberOfTransactionsByClient(@PathVariable("client-id") Integer clientID)
    {
        return ResponseEntity.ok(service.findNumberOfAllTransactionsByClient(clientID));
    }


    @PutMapping("client-id/{client-id}/update")
    public ResponseEntity<?> updateTransaction(@PathVariable("client-id") Integer clientID,
                                               @RequestBody Transaction transaction)
    {
        Transaction existTransaction = service.findTransaction(transaction.getTransactionID());
        if (existTransaction == null || !existTransaction.getClientID().equals(clientID)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No transaction found with ID "+transaction.getTransactionID());
        }
        service.updateTransaction(transaction);
        return ResponseEntity.ok(transaction);
    }

    @DeleteMapping("client-id/{client-id}/delete/{transaction-id}")
    public ResponseEntity<String> deleteTransaction(@PathVariable("client-id") Integer clientID,
                                                    @PathVariable("transaction-id") Integer transaction_id)
    {
        Transaction transaction = service.findTransaction(transaction_id);
        if (transaction == null || !transaction.getClientID().equals(clientID)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No transaction found with ID: " + transaction_id);
        }
        service.deleteTransaction(transaction_id);
        return ResponseEntity.ok("Transaction with ID " + transaction_id + " deleted successfully.");
    }

    @GetMapping("client-id/{client-id}/view/{transaction-id}")
    public ResponseEntity<?> findTransaction(@PathVariable("client-id") Integer clientID,
                                             @PathVariable("transaction-id") Integer transaction_id)
    {
        Transaction transaction = service.findTransaction(transaction_id);
        if (transaction == null || !transaction.getClientID().equals(clientID))
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No transaction found with ID: " + transaction_id);
        }
        return ResponseEntity.ok(transaction);
    }

    @GetMapping("client-id/{client-id}/report")
    public List<Transaction> filterTransactions(@PathVariable("client-id") Integer clientID,
                                                @RequestBody TransactionFilterRequest request)
    {
        if (request.getStartDate() == null) {
            request.setStartDate(LocalDate.of(2024, 1, 1));
        }
        if (request.getEndDate() == null) {
            request.setEndDate( LocalDate.now());
        }

        return service.filterTransactionsOfClient(clientID, request.getStartDate(),
                request.getEndDate(), request.getWalletTypes());
    }
}

