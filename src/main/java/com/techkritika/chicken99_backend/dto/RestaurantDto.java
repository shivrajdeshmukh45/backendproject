package com.techkritika.chicken99_backend.dto;

import lombok.Data;
import java.time.LocalTime;

@Data
public class RestaurantDto {
    private Long id;
    private String name;
    private String phoneNumber;
    private String email;
    private String slug;
    private String description;
    private String category;
    private String[] tags;
    private String logoUrl;
    private String bannerUrl;
    private String address;
    private String city;
    private String state;
    private String pincode;
    private LocalTime openingTime;
    private LocalTime closingTime;
    private Integer deliveryTime;
    private Double minOrderValue;
    private Boolean isPureVeg;
    private Boolean isFeatured;
    private Boolean deliveryAvailable;
    private Boolean takeawayAvailable;
    private Double deliveryCharge;
    private Double averageRating;
    private Integer reviewCount;
    private String[] paymentModes;
    private String status;
    private Boolean approvedByAdmin;
}
