package com.techkritika.chicken99_backend.service;

import com.techkritika.chicken99_backend.dto.OrderDto;
import java.util.List;

public interface OrderService {
    
    /**
     * Place a new order
     * @param orderDto The order details
     * @return The placed order with generated ID and details
     */
    OrderDto placeOrder(OrderDto orderDto);
    
    /**
     * Get order history for a specific customer
     * @param customerId The customer ID
     * @return List of orders for the customer, ordered by date descending
     */
    List<OrderDto> getOrderHistory(Long customerId);
}
