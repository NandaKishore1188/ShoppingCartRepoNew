package com.capg.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


import com.capg.dto.OrderDTO;
import com.capg.entity.Order;
import com.capg.entity.Product;
import com.capg.exceptions.OrderNotFoundException;
import com.capg.service.OrderService;
import com.capg.service.SequenceGeneratorService;


@RestController
@RequestMapping("/orders")
public class OrderController {
	
	@Autowired
	private OrderService orderService;
	
	@Autowired
	private SequenceGeneratorService service;
	
    @Autowired
    RestTemplate restTemplate;

    Order order = new Order();

    Product product = new Product();
	
//	@PostMapping("/save")
//	public ResponseEntity<OrderDTO> save(@Valid @RequestBody OrderDTO car) {
//		car.setOrderId(service.getSequenceNumber(Order.SEQUENCE_NAME));
//		return new ResponseEntity<OrderDTO>(orderService.createOrder(car),HttpStatus.CREATED);
//	}
	

    @PostMapping("/save")
    public ResponseEntity<String> saveOrder(@RequestBody OrderDTO order) throws OrderNotFoundException{

        String productname = order.getProductname();
        if(productname == null){
            throw new OrderNotFoundException("No Order Found with name "+productname);
        }
        else {
            Product product = restTemplate.getForObject("http://ShoppingCartProductService/products/getbyname/" +
            		productname, Product.class);
            double cost = product.getProductPrice() * order.getQuantity();
            order.setCost(cost);
            order.setOrderId(service.getSequenceNumber(Order.SEQUENCE_NAME));
            String x = "Your order with order Id " + order.getOrderId() + " with value " + cost + " is placed";
            OrderDTO savedOrder = orderService.createOrder(order);
            return ResponseEntity.ok(x);
        }

    }

	
	@GetMapping("/getall")
	public List<OrderDTO> orders(){
		return orderService.getOrders();
	}
	
	
	@GetMapping("/get/{orderId}")
	public OrderDTO orderById(@PathVariable Integer orderId) {
		return orderService.getOrder(orderId);
	}
	
	
	@PutMapping("/update/{orderId}")
	public ResponseEntity<Order> update(@PathVariable Integer orderId,@RequestBody Order prod) throws Exception{
		prod.setOrderId(orderId);
		return  ResponseEntity.ok().body(this.orderService.updateOrder(prod));
	}
	
	@DeleteMapping("/delete/{orderId}")
	public String delete(@PathVariable Integer orderId) {
		orderService.deleteOrder(orderId);
		return "Order With ID "+orderId+" Was Deleted Successfully!!"; 
	}
	
	@DeleteMapping("/deleteAll")
	public String deleteAll() {
		orderService.deleteAll();
		return "All Orders are Deleted Successfully!!";
	}
	
}