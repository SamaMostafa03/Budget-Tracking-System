package com.alibou.transaction;

import jakarta.persistence.*;
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
    @GeneratedValue
    private Integer transactionID;
    private double inflowAmount;
    private double outflowAmount;
    private String note;
    private String payee;
    private LocalDate transactionDate;
    private String walletType;
    private Integer budgetID;
    private Integer categoryID;
    private Integer targetID;
    private Integer clientID;
}
