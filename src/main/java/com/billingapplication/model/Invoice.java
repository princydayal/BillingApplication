package com.billingapplication.model;

import jakarta.persistence.*;

import java.util.List;
import java.util.Set;
import com.billingapplication.util.IDGenerator;
import lombok.*;

@Entity
@Table(name = "invoice")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Invoice {

    @Id
    private String invoiceId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "invoice_products",
            joinColumns = @JoinColumn(name = "invoice_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    private Set<Product> products;


    private String paymentName;
    private String email;
    private String paymentNum;
    private String address;

    @OneToMany(mappedBy = "invoice", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Cart> cartItems;


    private String gstin;
    private String billNum;
    private String billDate;
    private String termDueDate;
    private String customerphonenumber;
    private String totalAmount;
    private String totalAmountInWord;
    private String billMode; //Offline or online
    private String paymentMode; //COD or online
    private String status; //pending completed



    // Default constructor
//    public Invoice() {
//        this.invoiceId = IDGenerator.generateInvoiceId(); // Generate invoice ID
//        this.gstin = IDGenerator.generateGstin(); // Generate GSTIN
//        this.billNum = IDGenerator.generateBillNum(); // Generate Bill Number
//    }
//
//    // Parameterized constructor
//    public Invoice(String invoiceId, customer customer, Set<Product> products,
//                   String paymentName, String paymentNum, String address, String gstin,
//                   String billNum, String billDate, String termDueDate) {
//        this.invoiceId = invoiceId;
//        this.customer = customer;
//        this.products = products;
//        this.paymentName = paymentName;
//        this.paymentNum = paymentNum;
//        this.address = address;
//        this.gstin = gstin;
//        this.billNum = billNum;
//        this.billDate = billDate;
//        this.termDueDate = termDueDate;
//    }

    // Getters and Setters
}
