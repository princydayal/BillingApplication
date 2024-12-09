package com.billingapplication.repo;

import com.billingapplication.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepo extends JpaRepository<Payment, Long> {
    boolean existsByInvoiceNumber(String invoiceNumber); 
}
