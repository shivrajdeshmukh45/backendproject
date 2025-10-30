package com.techkritika.chicken99_backend.dto;

import lombok.Data;
import java.time.LocalTime;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@Data
public class AddRestaurantDto {
    
    @NotBlank(message = "Name is mandatory")
    private String name;
    private String phoneNumber;
    @Email
    @NotBlank(message = "Email is mandatory")
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
