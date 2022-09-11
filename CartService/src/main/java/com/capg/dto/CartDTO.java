package com.capg.dto;

import java.util.List;

import com.capg.entity.Cart;
import com.capg.entity.Items;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartDTO {
	
	private int cartId;

	private double totalPrice;
	
	private List<Items> items;
	
	public CartDTO(Cart cart){
		
		this.cartId=cart.getCartId();
		this.totalPrice=cart.getTotalPrice();
		
		this.items=cart.getItems();
	}

}
