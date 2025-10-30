package com.techkritika.chicken99_backend.dto;
import lombok.Setter;
import lombok.Getter;

import java.util.List;

@Getter
@Setter
public class CustomerDto {

    private Long id;
    private String name;
    private String email;
    private String phoneNumber;
    private Boolean isActive;
    private List<AddressDto> addresses;
    
}
