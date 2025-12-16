package com.budgetSystem.budget.Model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "category")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer categoryID;

    @NotEmpty(message = "categoryName is mandatory")
    @Column(nullable = false)
    private String categoryName;

    @NotNull(message = "client Id cannot be null")
    @Column(nullable = false)
    @Positive(message = "client Id cannot be negative or zero")
    private Integer clientId;

    @OneToMany(mappedBy = "category", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Budget> budgets;
}
