package com.capg.dto;

import com.capg.entity.Items;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemsDTO {

	private String productName;

	private double price;

	private int quantity;
	
	public ItemsDTO(Items items) {
		
		this.productName=items.getProductName();
		this.price=items.getPrice();
		this.quantity=items.getQuantity();
		
	}

}
