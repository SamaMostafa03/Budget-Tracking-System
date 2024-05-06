package com.budgetSystem.budget.Service;

 
 

import java.util.List;
import java.util.Optional;

import com.budgetSystem.budget.exceptions.RecordNotFoundExecption;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.budgetSystem.budget.Client.TransactionClient;
import com.budgetSystem.budget.Model.Target;
import com.budgetSystem.budget.Model.TargetDetails;
import com.budgetSystem.budget.Repository.TargetDetailsRepository;
import com.budgetSystem.budget.Repository.TargetRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TargetDetailsService {
    @Autowired
    private TargetDetailsRepository targetDetailsRepository;
    @Autowired
    private TransactionClient transactionClient;
    @Autowired
    private TargetRepository targetRepository;

    public TargetDetails calculateAndSaveRemainingAmount(Integer targetId, double assignedMoney)
    {
        Target target = targetRepository.findById(targetId).orElse(null);
        if (target != null)
        {
            double inflow = transactionClient.getInflowForUser(target.getClientID());
            double outflow = transactionClient.getOutflowForUser(target.getTargetID());
            inflow -= assignedMoney;
            double totalNeeded = target.getTotalMoneyNeeded();

            if(totalNeeded<assignedMoney)assignedMoney=totalNeeded;

            // Subtract assigned money from the total needed amount
            double remainingAmount = totalNeeded - assignedMoney;
            double targetStatuePercentage = (assignedMoney / totalNeeded) * 100;
            double availableMoney = assignedMoney - outflow;
            // Set the calculated remaining amount back to the Target object
            TargetDetails targetDetails = new TargetDetails();
            targetDetails.setMoneyAssigned(assignedMoney);
            targetDetails.setMoneyNeeded(remainingAmount);
            targetDetails.setTargetPercentage(targetStatuePercentage);
            targetDetails.setTotalWalletMoney(inflow);
            targetDetails.setMoneyAvailable(availableMoney);
            targetDetails.setFundedSpending(outflow);
            targetDetails.setActivityAmount(outflow);
            targetDetails.setTarget(target);
            if(assignedMoney<totalNeeded)
            {
                targetDetails.setTargetStatue(remainingAmount, target.getEndDate());
            }
            else
            {
                targetDetails.setTargetStatueFunded(outflow, totalNeeded);
            }
            return targetDetailsRepository.save(targetDetails);
        }

        throw new RecordNotFoundExecption("Target ID not found.");
    }
            
        public List<TargetDetails> getALLTargetDetails(Integer targetId)
        {
            Optional<Target> target = targetRepository.findById(targetId);
            if(target.isPresent())
            {
                List<TargetDetails> targetDetails = targetDetailsRepository.findByTarget(target.get());
                if (targetDetails.size()==0)throw new RecordNotFoundExecption("Target details not found");
                return targetDetails;
            }
            throw new RecordNotFoundExecption("Target id not found");
        }
        
             
    }

