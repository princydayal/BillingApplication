package com.billingapplication.repo;

import com.billingapplication.model.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, String> {
    List<Invoice> findByCustomerCustomerid(String customerId);
    List<Invoice> findByEmail(String email);

    @Query("SELECT COUNT(i) FROM Invoice i WHERE i.status = 'pending'")
    Integer getTotalPendingInvoices();

    @Query("SELECT COUNT(i) FROM Invoice i WHERE i.status = 'complete'")
    Integer getTotalCompleteInvoices();

    @Query("SELECT COUNT(i) FROM Invoice i")
    Integer getTotalInvoices();

}
