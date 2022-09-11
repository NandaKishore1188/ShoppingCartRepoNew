package com.capg.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import com.capg.dto.OrderDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection= "orders")
public class Order {
	
	@Transient
	public static String SEQUENCE_NAME= "order_sequence";

	@Id
	private Integer orderId;
	

	private String productname;
	

	private Integer quantity;
	

	private double cost;
	
	
	public Order(OrderDTO orderDTO) {
		this.orderId=orderDTO.getOrderId();
		this.productname=orderDTO.getProductname();
		this.quantity=orderDTO.getQuantity();
		this.cost=orderDTO.getCost();
	}
	
}

