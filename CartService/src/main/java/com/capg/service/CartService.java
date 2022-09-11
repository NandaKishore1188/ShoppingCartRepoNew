package com.capg.service;

import java.util.List;

import com.capg.dto.CartDTO;
import com.capg.entity.Cart;


public interface CartService {

	CartDTO addToCart(CartDTO cartDTO);

	List<CartDTO> getAllCarts();

	CartDTO getCartById(Integer cartId);
	
	Cart updateCart(Cart cart) throws Exception;

}
