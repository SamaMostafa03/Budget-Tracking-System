package com.budgetSystem.budget.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

import java.time.LocalDate;

@Setter
@Getter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "budget")
public class Budget {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer budgetID;

    @NotNull(message = "budgetPeriod cannot be null")
    @FutureOrPresent(message = "budgetPeriod cannot be past date or empty")
    private LocalDate budgetPeriod;

    @NotNull(message = "clientID cannot be null")
    @Positive(message = "clientID cannot be negative")
    private Integer clientID;


}