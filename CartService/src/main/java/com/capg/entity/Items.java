package com.capg.entity;

import com.capg.dto.ItemsDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Items {
	
	private String productName;
	
	private double price;
	
	private int quantity;

	public Items(ItemsDTO itemsDTO) {
		
		this.productName=itemsDTO.getProductName();
		this.price=itemsDTO.getPrice();
		this.quantity=itemsDTO.getQuantity();
		
	}
}
