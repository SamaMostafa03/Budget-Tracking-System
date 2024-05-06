package com.budgetSystem.budget.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.budgetSystem.budget.Model.Target;
import com.budgetSystem.budget.Model.TargetDetails;
import java.util.List;


@Repository
public interface TargetDetailsRepository extends JpaRepository<TargetDetails,Integer> {
  List<TargetDetails> findByTarget(Target target);
}
