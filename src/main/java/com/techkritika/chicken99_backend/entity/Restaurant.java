package com.techkritika.chicken99_backend.entity;


import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalTime;

@Data
@Entity
@Table(name = "restaurants")
public class Restaurant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String phoneNumber;
    private String email;
    private String slug;
    private String description;
    private String category;
    
    @ElementCollection
    @CollectionTable(name = "restaurant_tags", joinColumns = @JoinColumn(name = "restaurant_id"))
    @Column(name = "tag")
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
    
    @ElementCollection
    @CollectionTable(name = "restaurant_payment_modes", joinColumns = @JoinColumn(name = "restaurant_id"))
    @Column(name = "payment_mode")
    private String[] paymentModes;
    
    private String status;
    private Boolean approvedByAdmin;
  
}
