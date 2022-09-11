package com.capg.dto;

import com.capg.entity.Order;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDTO {
	
	private Integer orderId;
	

	private String productname;
	

	private Integer quantity;
	

	private double cost;
	
	
	
	public OrderDTO(Order order) {
		this.orderId=order.getOrderId();
		this.productname=order.getProductname();
		this.quantity=order.getQuantity();
		this.cost=order.getCost();
		
	}
}
