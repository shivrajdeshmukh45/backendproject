package com.techkritika.chicken99_backend.controller;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import com.techkritika.chicken99_backend.dto.OrderDto;
import com.techkritika.chicken99_backend.service.OrderService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/customers/{customerId}/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<OrderDto> placeOrder(@PathVariable Long customerId, @RequestBody OrderDto orderDTO) {
        // Set the customer ID in the order DTO
        
        orderDTO.setCustomerId(customerId);
        return ResponseEntity.ok(orderService.placeOrder(orderDTO));

    }

    @GetMapping
    public ResponseEntity<List<OrderDto>> getOrderHistory(@PathVariable Long customerId) {
        return ResponseEntity.ok(orderService.getOrderHistory(customerId));
    }
}
