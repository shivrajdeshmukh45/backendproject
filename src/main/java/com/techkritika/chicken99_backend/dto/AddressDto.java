package com.techkritika.chicken99_backend.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddressDto {
    private Long id;
    private String addressType; // HOME, WORK, BILLING, SHIPPING, OTHER
    private String street;
    private String apartment;
    private String city;
    private String state;
    private String zipCode;
    private String country;
    private String landmark;
    private Boolean isDefault;
    private Boolean isActive;
}