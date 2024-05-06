package com.budgetSystem.transaction;

import com.budgetSystem.transaction.exceptions.RecordNotFoundExecption;
import jakarta.persistence.*;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer transactionID;

    @NotNull(message = "inflowAmount cannot be null")
    @PositiveOrZero(message = "inflowAmount cannot be negative or empty")
    private double inflowAmount;

    @NotNull(message = "outflowAmount cannot be null")
    @PositiveOrZero(message = "outflowAmount cannot be negative or empty")
    private double outflowAmount;

    @NotNull(message = "note cannot be null")
    private String note;

    @NotNull(message = "payee cannot be null")
    private String payee;

    @NotNull(message = "transactionDate cannot be null")
    @FutureOrPresent(message = "transactionDate cannot be past date or empty")
    private LocalDate transactionDate;

    @NotEmpty(message = "walletType is mandatory")
    @Pattern(regexp = "^(cash|visa|bank|debit)$", message = "Wallet type must be cash, visa, bank, or debit")
    private String walletType;

    @NotNull(message = "budgetID cannot be null")
    @Positive(message = "budgetID cannot be negative")
    private Integer budgetID;

    @NotNull(message = "categoryID cannot be null")
    @Positive(message = "categoryID cannot be negative")
    private Integer categoryID;

    @NotNull(message = "targetID cannot be null")
    @Positive(message = "targetID cannot be negative")
    private Integer targetID;

    @NotNull(message = "clientID cannot be null")
    @Positive(message = "clientID cannot be negative")
    private Integer clientID;

}
