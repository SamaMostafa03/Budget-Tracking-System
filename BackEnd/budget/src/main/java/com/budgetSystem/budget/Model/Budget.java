package com.budgetSystem.budget.Model;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Setter
@Getter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "budget")
public class Budget{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer budgetId;

    @NotNull(message = "endDate cannot be null")
    @Column(nullable = false)
    @FutureOrPresent(message = "endDate must be future or current date only")
    private LocalDate endDate;

    @NotEmpty(message = "budget Name is mandatory")
    @Column(nullable = false)
    private String budgetName;

    @NotNull(message = "totalMoneyNeeded cannot be null")
    @Column(nullable = false)
    @Positive(message = "totalMoneyNeeded cannot be negative or zero")
    private BigDecimal totalAmount;

    @NotNull(message = "remainingAmount cannot be null")
    @Column(nullable = false)
    @PositiveOrZero(message = "remainingAmount cannot be negative")
    private BigDecimal remainingAmount;

    @NotEmpty(message = "budget status is mandatory")
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private BudgetStatus budgetStatus;

    @NotNull(message = "clientID cannot be null")
    @Column(nullable = false)
    @Positive(message = "clientID cannot be negative")
    private Integer clientId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

}