package com.budgetSystem.transaction;
 
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/transaction")
@RequiredArgsConstructor
public class TransactionController {
    @Autowired
    private final TransactionService service;
    SuccessResponse successResponce = new SuccessResponse();

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> saveTransaction(@Valid @RequestBody Transaction transaction)
    {
        service.saveTransaction(transaction);
        return ResponseEntity.ok(successResponce);
    }

    @GetMapping
    public ResponseEntity<?> findAllTransactions()
    {
        return ResponseEntity.ok(new SuccessResponse(service.findAllTransactions()));
    }

    @GetMapping("client/{client-id}")
    public ResponseEntity<?> findAllTransactionsByClient(@PathVariable("client-id") Integer clientID)
    {
        return ResponseEntity.ok(new SuccessResponse(service.findAllTransactionsByClient(clientID)));
    }

    @GetMapping("client/{client-id}/transactions-number")
    public ResponseEntity<?> findNumberOfTransactionsByClient(@PathVariable("client-id") Integer clientID)
    {
        return ResponseEntity.ok(new SuccessResponse(service.findNumberOfAllTransactionsByClient(clientID)));
    }

    @GetMapping("client/{client-id}/view/{transaction-id}")
    public ResponseEntity<?> findTransaction(@PathVariable("client-id") Integer clientID,
                                             @PathVariable("transaction-id") Integer transaction_id)
    {
        Transaction transaction = service.findTransaction(clientID,transaction_id);
        return ResponseEntity.ok(new SuccessResponse(transaction));
    }

    @PutMapping("client/{client-id}/update")
    public ResponseEntity<?> updateTransaction(@PathVariable("client-id") Integer clientID,
                                               @Valid @RequestBody Transaction transaction)
    {
        findTransaction(clientID,transaction.getTransactionID());
        service.updateTransaction(transaction);
        return ResponseEntity.ok(successResponce);
    }

    @DeleteMapping("client/{client-id}/delete/{transaction-id}")
    public ResponseEntity<?> deleteTransaction(@PathVariable("client-id") Integer clientID,
                                                    @PathVariable("transaction-id") Integer transaction_id)
    {
        findTransaction(clientID,transaction_id);
        service.deleteTransaction(transaction_id);
        return ResponseEntity.ok(successResponce);
    }


    @GetMapping("client/{client-id}/report")
    public ResponseEntity<?> filterTransactionForReaport(@PathVariable("client-id") Integer clientID,
                                                @RequestBody TransactionFilterRequestDTO request)
    {
        if (request.getStartDate() == null) {
            request.setStartDate(LocalDate.of(2024, 1, 1));
        }
        if (request.getEndDate() == null) {
            request.setEndDate( LocalDate.now());
        }

        return ResponseEntity.ok(new SuccessResponse(service.filterTransactionsOfClient(clientID, request.getStartDate(),
                request.getEndDate(), request.getWalletTypes())));
    }

    @GetMapping("client/{client-id}/wallet/{wallet-type}")
    public ResponseEntity<?> getTransactionByWallet(@PathVariable("client-id") Integer clientID,
                                                       @PathVariable("wallet-type") String walletType)
    {
        return ResponseEntity.ok(new SuccessResponse(service.CheckForTransactionsOfClientByWallet(clientID,walletType)));
    }

    @GetMapping("/getinflow/{clientId}")
    public double getInflowForUser(@PathVariable("clientId") int clientId) {
        double inflowByWalletType = service.calculateTotalInflowForUser(clientId);
        return inflowByWalletType;
    }

    @GetMapping("/getoutflow/{targetId}")
    public double getOutflowForUser(@PathVariable("targetId") int targetId) {
        double outflow = service.calculateOutflowForUser(targetId);
        return outflow;
    }

    @GetMapping("client/{clientId}/wallet/{wallet-type}/inflow")
    public ResponseEntity<?> getInflowForWalletByUser(@PathVariable("clientId") int clientId ,
                                                      @PathVariable("wallet-type") String walletType)
    {
        double walletInflow = service.calculateWalletInflowForUser(clientId,walletType);
        return ResponseEntity.ok(new SuccessResponse(walletInflow));
    }

}

