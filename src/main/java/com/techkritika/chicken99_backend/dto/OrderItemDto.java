package com.techkritika.chicken99_backend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemDto{
    private Long menuItemId;
    private String menuItemName;
    private int quantity;
    private double totalPrice;
}

