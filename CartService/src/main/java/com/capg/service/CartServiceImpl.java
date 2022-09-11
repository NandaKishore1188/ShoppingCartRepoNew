package com.capg.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capg.dto.CartDTO;
import com.capg.entity.Cart;
import com.capg.repository.CartRepository;

@Service
public class CartServiceImpl implements CartService{
	
	@Autowired
	private CartRepository cartRepository;
	
	@Override
	public CartDTO addToCart(CartDTO cartDTO) {
		// TODO Auto-generated method stub
		Cart cart = new Cart(cartDTO);
		return new CartDTO(cartRepository.save(cart));	
	}

	@Override
	public List<CartDTO> getAllCarts() {
		// TODO Auto-generated method stub
		List<Cart> cart = cartRepository.findAll();
		return cart.stream().map(CartDTO::new).collect(Collectors.toList());
	}

	@Override
	public CartDTO getCartById(Integer cartId) {
		// TODO Auto-generated method stub
		Cart cart = cartRepository.findById(cartId).orElseThrow() ;
		return new CartDTO(cart);
	}

	@Override
	public Cart updateCart(Cart cart) throws Exception {
		// TODO Auto-generated method stub
		Optional<Cart> user = this.cartRepository.findById(cart.getCartId());
		if (user.isPresent()) {
			Cart cartUpdate = user.get();
			cartUpdate.setTotalPrice(cart.getTotalPrice());
			cartUpdate.setItems(cart.getItems());
			cartRepository.save(cartUpdate);
	        return cartUpdate;
	    } else {
	        throw new Exception("Record not found with id : " + cart.getCartId());
	    }
	}
	
	

}
