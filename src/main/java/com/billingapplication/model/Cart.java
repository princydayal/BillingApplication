package com.billingapplication.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = true)
    private String productid;
    private String userid;
    private String name;
    private String price;
    private String quantity;
    private String image;
    private String  description;
    private String cartQuantity;


    //	@ManyToOne(fetch = FetchType.LAZY)
//	@JoinColumn(name = "category_id", nulslable = false)
    private String category;

    @ManyToOne
    @JoinColumn(name = "invoice_id") // Foreign key column in Cart table
    private Invoice invoice;



}
