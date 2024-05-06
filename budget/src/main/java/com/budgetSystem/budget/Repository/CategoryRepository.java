package com.budgetSystem.budget.Repository;


import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.budgetSystem.budget.Model.Category;


@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {

    Optional<Category> findByClientIDAndCategoryID(Integer clientID, Integer categoryID);

    Category findByCategoryID(Integer categoryID);
     List<Category> findAllByClientID(Integer clientID);
}
