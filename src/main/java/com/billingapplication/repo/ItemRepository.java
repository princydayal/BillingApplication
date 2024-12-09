package com.billingapplication.repo;

import com.billingapplication.model.ExpenseItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<ExpenseItem, Long> {
    List<ExpenseItem> findByItemname(String itemName);
}
