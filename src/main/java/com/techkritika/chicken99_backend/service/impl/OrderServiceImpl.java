package com.techkritika.chicken99_backend.service.impl;

import com.techkritika.chicken99_backend.dto.OrderDto;
import com.techkritika.chicken99_backend.dto.OrderItemDto;
import com.techkritika.chicken99_backend.entity.*;
import com.techkritika.chicken99_backend.enums.OrderStatus;
import com.techkritika.chicken99_backend.exception.ResourceNotFoundException;
import com.techkritika.chicken99_backend.repository.*;
import com.techkritika.chicken99_backend.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;
    private final RestaurantRepository restaurantRepository;
    private final MenuRepository menuRepository;

    // Place new order
    @Override
    public OrderDto placeOrder(OrderDto orderDto) {
        Customer customer = customerRepository.findById(orderDto.getCustomerId())
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found"));
        Restaurant restaurant = restaurantRepository.findById(orderDto.getRestaurantId())
                .orElseThrow(() -> new ResourceNotFoundException("Restaurant not found"));

        Order order = new Order();
        order.setCustomer(customer);
        order.setRestaurant(restaurant);
        order.setOrderDate(LocalDateTime.now());
        order.setStatus(OrderStatus.PENDING);
        order.setDeliveryAddress(orderDto.getDeliveryAddress());
        order.setPaymentMethod(orderDto.getPaymentMethod());

        List<OrderItem> items = orderDto.getItems().stream().map(itemDto -> {
            MenuItem menuItem = menuRepository.findById(itemDto.getMenuItemId())
                    .orElseThrow(() -> new ResourceNotFoundException("Menu item not found"));

            OrderItem item = new OrderItem();
            item.setMenuItem(menuItem);
            item.setQuantity(itemDto.getQuantity());
            item.setItemPrice(menuItem.getPrice());
            item.setTotalPrice(menuItem.getPrice() * itemDto.getQuantity());
            item.setOrder(order);
            return item;
        }).collect(Collectors.toList());

        double total = items.stream().mapToDouble(OrderItem::getTotalPrice).sum();
        order.setTotalAmount(total);
        order.setOrderItems(items);

        Order savedOrder = orderRepository.save(order);

        return convertToDTO(savedOrder);
    }

    // Fetch customer order history
    @Override
    public List<OrderDto> getOrderHistory(Long customerId) {
        return orderRepository.findByCustomerIdOrderByOrderDateDesc(customerId)
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    // Helper for DTO conversion
    private OrderDto convertToDTO(Order order) {
        OrderDto dto = new OrderDto();
        dto.setId(order.getId());
        dto.setCustomerId(order.getCustomer().getId());
        dto.setRestaurantId(order.getRestaurant().getId());
        dto.setRestaurantName(order.getRestaurant().getName());
        dto.setOrderDate(order.getOrderDate());
        dto.setTotalAmount(order.getTotalAmount());
        dto.setDeliveryAddress(order.getDeliveryAddress());
        dto.setPaymentMethod(order.getPaymentMethod());
        dto.setStatus(order.getStatus().toString());

        List<OrderItemDto> items = order.getOrderItems().stream().map(item -> {
            OrderItemDto i = new OrderItemDto();
            i.setMenuItemId(item.getMenuItem().getId());
            i.setMenuItemName(item.getMenuItem().getName());
            i.setQuantity(item.getQuantity());
            i.setTotalPrice(item.getTotalPrice());
            return i;
        }).collect(Collectors.toList());

        dto.setItems(items);
        return dto;
    }
}
