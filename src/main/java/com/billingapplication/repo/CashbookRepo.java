package com.billingapplication.repo;

import com.billingapplication.model.Cashbook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CashbookRepo extends JpaRepository<Cashbook, String> {
    // Additional custom query methods can be defined here if needed
    // Query for sum where entry_mode is 'IN'
    @Query("SELECT SUM(CAST(c.amount AS double )) FROM Cashbook c WHERE c.entry_mode = 'IN'")
    Double getTotalIn();

    // Query for sum where entry_mode is 'OUT'
    @Query("SELECT SUM(CAST(c.amount AS double )) FROM Cashbook c WHERE c.entry_mode = 'OUT'")
    Double getTotalOut();
}
