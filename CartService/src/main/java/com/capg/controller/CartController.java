package com.capg.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capg.dto.CartDTO;
import com.capg.entity.Cart;
import com.capg.service.CartService;
import com.capg.service.SequenceGeneratorService;

@RestController
@RequestMapping("/cart")
public class CartController {
	
	@Autowired
	private CartService cartService;
	
	@Autowired
	private SequenceGeneratorService service;
	
	@PostMapping("/save")
	public ResponseEntity<CartDTO> save(@Valid @RequestBody CartDTO car) {
		car.setCartId(service.getSequenceNumber(Cart.SEQUENCE_NAME));
		return new ResponseEntity<CartDTO>(cartService.addToCart(car),HttpStatus.CREATED);
	}
	
	@GetMapping("/getall")
	public List<CartDTO> product(){
		return cartService.getAllCarts();
	}
	
	@GetMapping("/get/{cartId}")
	public CartDTO byProductId(@PathVariable Integer cartId) {
		return cartService.getCartById(cartId);
	}
	
	@PutMapping("/update/{cartId}")
	public ResponseEntity<Cart> update(@PathVariable Integer cartId,@RequestBody Cart prod) throws Exception{
		prod.setCartId(cartId);
		return  ResponseEntity.ok().body(this.cartService.updateCart(prod));
	}
}
