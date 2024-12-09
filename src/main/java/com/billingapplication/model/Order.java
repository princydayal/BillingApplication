package com.billingapplication.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date orderDate;

    @Column(nullable = false)
    private Double totalAmount;

    @Column(name = "user_email", nullable = false)
    private String userEmail;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "order_products",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    @EqualsAndHashCode.Exclude
    private Set<Product> products = new HashSet<>();

    @OneToOne(mappedBy = "order", fetch = FetchType.LAZY)
    @JsonBackReference(value = "order-payment")
    private Payment payment;

    @Column(nullable = false, unique = true)
    private String orderNumber; // Unique 4 to 6 digit order number




	
    
}
