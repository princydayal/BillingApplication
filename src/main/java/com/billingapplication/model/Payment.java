package com.billingapplication.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.Random;

import com.billingapplication.repo.PaymentRepo;

@Entity
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class Payment {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Double amount;

    @Column(nullable = false)
    private String method; // e.g., Credit Card, PayPal, etc.

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date paymentDate;

    @Column(name = "user_email", nullable = false)
    private String userEmail;

    @OneToOne
    @JoinColumn(name = "order_id", nullable = false)
    private Order order; // Assuming there's an Order class to link to

    @Column(unique = true, nullable = false)
    private String invoiceNumber; // Unique identifier for the invoice

    @Column(nullable = false)
    private String status; // e.g., Pending, Completed, Failed

    @Column(nullable = false)
    private String transactionId; // Reference from payment gateway

    @Column(nullable = false)
    private String currency; // e.g., USD, EUR

    private String paymentDescription; // Description of the payment

    private String billingAddress; // Billing address for the invoice, if applicable

    // Method to generate a unique 8-digit invoice number
    public void generateInvoiceNumber(PaymentRepo paymentRepository) {
        String newInvoiceNumber;
        Random random = new Random();
        do {
            newInvoiceNumber = String.format("%08d", random.nextInt(100000000));
        } while (paymentRepository.existsByInvoiceNumber(newInvoiceNumber));
        
        this.invoiceNumber = newInvoiceNumber;
    }

	public Payment(Long id, Double amount, String method, Date paymentDate, String userEmail, Order order,
			String invoiceNumber, String status, String transactionId, String currency, String paymentDescription,
			String billingAddress) {
		super();
		this.id = id;
		this.amount = amount;
		this.method = method;
		this.paymentDate = paymentDate;
		this.userEmail = userEmail;
		this.order = order;
		this.invoiceNumber = invoiceNumber;
		this.status = status;
		this.transactionId = transactionId;
		this.currency = currency;
		this.paymentDescription = paymentDescription;
		this.billingAddress = billingAddress;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public Date getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(Date paymentDate) {
		this.paymentDate = paymentDate;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public String getInvoiceNumber() {
		return invoiceNumber;
	}

	public void setInvoiceNumber(String invoiceNumber) {
		this.invoiceNumber = invoiceNumber;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getPaymentDescription() {
		return paymentDescription;
	}

	public void setPaymentDescription(String paymentDescription) {
		this.paymentDescription = paymentDescription;
	}

	public String getBillingAddress() {
		return billingAddress;
	}

	public void setBillingAddress(String billingAddress) {
		this.billingAddress = billingAddress;
	}

	@Override
	public String toString() {
		return "Payment [id=" + id + ", amount=" + amount + ", method=" + method + ", paymentDate=" + paymentDate
				+ ", userEmail=" + userEmail + ", order=" + order + ", invoiceNumber=" + invoiceNumber + ", status="
				+ status + ", transactionId=" + transactionId + ", currency=" + currency + ", paymentDescription="
				+ paymentDescription + ", billingAddress=" + billingAddress + ", getId()=" + getId() + ", getAmount()="
				+ getAmount() + ", getMethod()=" + getMethod() + ", getPaymentDate()=" + getPaymentDate()
				+ ", getUserEmail()=" + getUserEmail() + ", getOrder()=" + getOrder() + ", getInvoiceNumber()="
				+ getInvoiceNumber() + ", getStatus()=" + getStatus() + ", getTransactionId()=" + getTransactionId()
				+ ", getCurrency()=" + getCurrency() + ", getPaymentDescription()=" + getPaymentDescription()
				+ ", getBillingAddress()=" + getBillingAddress() + ", getClass()=" + getClass() + ", hashCode()="
				+ hashCode() + ", toString()=" + super.toString() + "]";
	}


    
    
}
