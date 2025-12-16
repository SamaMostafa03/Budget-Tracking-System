package com.budgetSystem.transaction;
 
import com.budgetSystem.transaction.dto.CreateTransactionReq;
import com.budgetSystem.transaction.dto.TransactionFilterRequest;
import com.budgetSystem.transaction.dto.TransactionResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transaction")
@RequiredArgsConstructor
public class TransactionController {
    private final TransactionService service;
    @GetMapping("/health")
    public String test() throws InterruptedException {
        Thread.sleep(200);
        return "OK";
    }

    @PostMapping("/create")
    public ResponseEntity<TransactionResponse> saveTransaction(@Valid @RequestBody CreateTransactionReq request)
    {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(service.saveTransaction(request));
    }


    @GetMapping("/client/{clientId}")
    public ResponseEntity<List<TransactionResponse>> findAllTransactionsByClient(@Valid @PathVariable Integer clientId)
    {
        return ResponseEntity.status(HttpStatus.OK).body(service.findAllTransactionsByClient(clientId));
    }

    @GetMapping("/client/{clientId}/total-number")
    public ResponseEntity<?> findTotalNumberOfTransactionsByClient(@Valid @PathVariable Integer clientId)
    {
        return ResponseEntity.status(HttpStatus.OK)
                .body(service.findTotalNumberOfAllTransactionsByClient(clientId));
    }

    @GetMapping("/find/{transactionId}")
    public ResponseEntity<TransactionResponse> findTransaction(@Valid @PathVariable Integer transactionId)
    {
        return ResponseEntity.status(HttpStatus.OK)
                .body(service.findTransaction(transactionId));
    }

    @PostMapping("/filter")
    public ResponseEntity<List<TransactionResponse>> filterTransaction(@Valid @RequestBody TransactionFilterRequest request)
    {
        return ResponseEntity.status(HttpStatus.OK)
                .body(service.filterTransactions(request));
    }

}

