package com.budgetSystem.budget.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.budgetSystem.budget.Model.Category;
import com.budgetSystem.budget.Model.Target;
import java.util.List;


@Repository
public interface TargetRepository extends JpaRepository<Target,Integer>{
   List<Target> findByCategory(Category category);

   List<Target> findByClientID(Integer clientID);

   List<Target> findAllByClientID(Integer clientID);
} 
