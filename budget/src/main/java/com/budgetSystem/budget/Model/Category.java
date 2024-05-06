package com.budgetSystem.budget.Model;
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
@Table(name = "category")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer categoryID;

    @NotEmpty(message = "categoryName is mandatory")
    private String categoryName;

    @NotNull(message = "budgetID cannot be null")
    @Positive(message = "budgetID cannot be negative")
    private Integer budgetID;

    @NotNull(message = "clientID cannot be null")
    @Positive(message = "clientID cannot be negative")
    private Integer clientID;


}
