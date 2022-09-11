package com.capg.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.capg.entity.Cart;

public interface CartRepository extends MongoRepository<Cart, Integer>{

}
