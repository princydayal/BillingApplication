package com.billingapplication.model;



import jakarta.persistence.*;
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
public class Product {
	
	@Id
	 @Column(nullable = false, unique = true)
	private String productid;
	private String name;
	private String price;
	private String quantity;
	private String image;
	private String  description;
	private String cartQuantity;

//	@ManyToOne(fetch = FetchType.LAZY)
//	@JoinColumn(name = "category_id", nulslable = false)
	private String category;

}
