package com.budgetSystem.budget.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Setter
@Getter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "target")
public class Target {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer targetID;

    @NotNull(message = "endDate cannot be null")
    @Future(message = "endDate must be future date only")
    private LocalDate endDate;

    @NotEmpty(message = "targetName is mandatory")
    private String targetName;

    @NotNull(message = "totalMoneyNeeded cannot be null")
    @Positive(message = "totalMoneyNeeded cannot be negative or zero")
    private double totalMoneyNeeded;
     @JsonBackReference
    @ManyToOne(cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
     private Category category;
    // @JsonBackReference
    @OneToMany(mappedBy = "target", cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private List<TargetDetails> targetDetails;
    @NotNull(message = "clientID cannot be null")
    @Positive(message = "clientID cannot be negative")
    private Integer clientID;
   
    public Target(String targetName, LocalDate endDate, double totalMoneyNeeded, Category category, int clientID) {
        this.endDate = endDate;
        this.targetName = targetName;
        this.totalMoneyNeeded = totalMoneyNeeded;
        this.category = category;
        this.clientID = clientID;
    }
}