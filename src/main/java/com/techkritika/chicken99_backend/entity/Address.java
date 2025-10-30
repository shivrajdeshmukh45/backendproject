package com.techkritika.chicken99_backend.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "addresses")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String addressType; // HOME, WORK, BILLING, SHIPPING, OTHER

    @Column(nullable = false)
    private String street;

    private String apartment; // Apartment, suite, unit number (optional)

    @Column(nullable = false)
    private String city;

    @Column(nullable = false)
    private String state;

    @Column(nullable = false)
    private String zipCode;

    @Column(nullable = false)
    private String country;

    private String landmark; // Optional landmark for easier delivery

    @Column(name = "is_default")
    private Boolean isDefault = false; // Mark one address as default

    @Column(name = "is_active")
    private Boolean isActive = true; // Soft delete support

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    
    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;



    // Many addresses belong to one customer
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", nullable = false)
    
    private Customer customer;
}