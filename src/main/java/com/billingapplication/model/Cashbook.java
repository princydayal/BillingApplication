package com.billingapplication.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Cashbook {

    @Id
    @Column(name = "cashbook_id", nullable = false, unique = true)
    private String cashbook_id;

    private String name;
    private String amount;
    private String description;
    private String payment_mode;
    private String date;
    private String entry_mode;

    // Getters and Setters for the new field

    
}
