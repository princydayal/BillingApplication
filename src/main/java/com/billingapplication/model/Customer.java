package com.billingapplication.model;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Customer {

	@Id
	@Column(name = "id", updatable = false, nullable = false)
	private String customerid;

	@Column(nullable = false, length = 50)
    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    private String password;


    private String phonenumber;
    private Boolean isAuthenticated = false;

//    @Column(unique = true, nullable = false)
//    private String customerid;
    
    private String address;
    private String balance;
    private String message;




	
    
}