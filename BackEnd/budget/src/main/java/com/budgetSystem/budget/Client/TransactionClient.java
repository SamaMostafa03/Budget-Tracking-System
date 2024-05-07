package com.budgetSystem.budget.Client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
 
 @Configuration
@FeignClient(name = "transaction-service",url = "${application.config.transactions-url}")
public interface TransactionClient {

   @GetMapping("/getinflow/{clientId}")
   public double getInflowForUser(@PathVariable("clientId") int clientId);
   
   @GetMapping("/getoutflow/{targetId}")
   public double getOutflowForUser(@PathVariable("targetId") int targetId);

}

 