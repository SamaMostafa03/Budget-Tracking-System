package com.budgetSystem.budget.Model;


import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Setter
@Getter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "detailsTarget")
public class TargetDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer targetDetailsID;

    private double moneyAssigned;

    private double targetPercentage;

    private double moneyNeeded;

    private double moneyAvailable;

    private double totalWalletMoney;

    private double fundedSpending;

    private String targetStatue;

    private double activityAmount;
    @JsonBackReference
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "target_id")
    private Target target;
    public void setTargetStatue(double totalMoneyNeeded, LocalDate endDate) {
       
        this.targetStatue = totalMoneyNeeded + " needed by " + endDate;
    }
   
    public void setTargetStatueFunded(double activityAmount, double totalMoneyNeeded) {
        
        this.targetStatue =  " Funded " + " spent " +this.activityAmount+" of " +totalMoneyNeeded;
    }
    public String getTargetStatue() {
        return this.targetStatue;
    }
 

}
