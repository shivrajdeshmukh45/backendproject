package com.techkritika.chicken99_backend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;
import java.util.List;



@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderDto {
    private Long id;
    private Long customerId;
    private Long restaurantId;
    private String restaurantName;
    private LocalDateTime orderDate;
    private List<OrderItemDto> items;
    private Double totalAmount;
    private String deliveryAddress;
    private String paymentMethod;
    private String status;


}
