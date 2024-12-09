package com.billingapplication.service;

import com.billingapplication.model.Payment;
import com.billingapplication.repo.PaymentRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepo paymentRepository;

    @Autowired
    private UserService userService; // Service to check if email is registered

    @Transactional
    public Payment createPayment(Payment payment) {
        if (!userService.isEmailRegistered(payment.getUserEmail())) {
            throw new RuntimeException("Email not registered: " + payment.getUserEmail());
        }

        // Generate a unique invoice number
        payment.generateInvoiceNumber(paymentRepository);

        return paymentRepository.save(payment);
    }

    @Transactional
    public Payment updatePayment(Long paymentId, Payment paymentDetails) {
        // Check if the payment entry exists
        Payment existingPayment = paymentRepository.findById(paymentId)
                .orElseThrow(() -> new IllegalArgumentException("Payment with ID " + paymentId + " does not exist."));

        // Check if the user with the given email exists
        if (!userService.isEmailRegistered(paymentDetails.getUserEmail())) {
            throw new IllegalArgumentException("User with email " + paymentDetails.getUserEmail() + " does not exist.");
        }

        // Update the existing payment entry fields
        existingPayment.setAmount(paymentDetails.getAmount());
        existingPayment.setMethod(paymentDetails.getMethod());
        existingPayment.setPaymentDate(paymentDetails.getPaymentDate());
        existingPayment.setUserEmail(paymentDetails.getUserEmail());
        existingPayment.setStatus(paymentDetails.getStatus());
        existingPayment.setTransactionId(paymentDetails.getTransactionId());
        existingPayment.setCurrency(paymentDetails.getCurrency());
        existingPayment.setPaymentDescription(paymentDetails.getPaymentDescription());
        existingPayment.setBillingAddress(paymentDetails.getBillingAddress());

        // Save the updated payment entry
        return paymentRepository.save(existingPayment);
    }

    public void deletePayment(Long paymentId) {
        // Check if the payment entry exists
        if (!paymentRepository.existsById(paymentId)) {
            throw new IllegalArgumentException("Payment with ID " + paymentId + " does not exist.");
        }

        // Delete the payment entry
        paymentRepository.deleteById(paymentId);
    }

    public List<Payment> getAllPayments() {
        return paymentRepository.findAll();
    }

    public Payment getPaymentById(Long paymentId) {
        return paymentRepository.findById(paymentId)
                .orElseThrow(() -> new IllegalArgumentException("Payment with ID " + paymentId + " does not exist."));
    }
}
