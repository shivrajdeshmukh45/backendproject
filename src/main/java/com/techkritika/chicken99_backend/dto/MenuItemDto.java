package com.techkritika.chicken99_backend.dto;

import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

@Getter
@Setter
public class MenuItemDto {
    private Long id;
    private Integer menuItemNumber;
    private String name;
    private String description;
    private Double price;
    private String category;
    private String subcategory;
    private String imageUrl;
    private Boolean isAvailable;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
}
