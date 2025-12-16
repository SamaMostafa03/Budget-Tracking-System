package com.budgetSystem.transaction.model;
 
import jakarta.persistence.*;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "transactions")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer transactionID;

    @NotNull(message = "Transaction Amount cannot be null")
    @Column(nullable = false)
    @Positive(message = "Transaction Amount cannot be negative or zero")
    private BigDecimal amount;

    @NotNull(message = "note cannot be null")
    @Column(nullable = false)
    private String note;

    @NotNull(message = "Transaction Date cannot be null")
    @Column(nullable = false)
    @PastOrPresent(message = "Transaction Date cannot be future date or empty")
    private LocalDate transactionDate;

    @NotNull(message = "Transaction type cannot be null")
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private TransactionType type;

    @NotNull(message = "budgetID cannot be null")
    @Column(nullable = false)
    @Positive(message = "budgetID cannot be negative")
    private Integer budgetId;

    @NotNull(message = "clientID cannot be null")
    @Column(nullable = false)
    @Positive(message = "clientID cannot be negative")
    private Integer clientId;

    @NotNull(message = "Wallet id cannot be null")
    @Column(nullable = false)
    @Positive(message = "Wallet id cannot be negative")
    private Integer walletId;

}
