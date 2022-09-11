package com.capg.entity;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import com.capg.dto.CartDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection= "carts")
public class Cart {
	@Transient
	public static String SEQUENCE_NAME;

	@Id
	private int cartId;
	
	private double totalPrice;
	
	private List<Items> items;
	
	public Cart(CartDTO cartDTO) {
		this.cartId=cartDTO.getCartId();
		this.totalPrice=cartDTO.getTotalPrice();
		
		this.items=cartDTO.getItems();
	}

}
