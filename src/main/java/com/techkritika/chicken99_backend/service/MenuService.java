package com.techkritika.chicken99_backend.service;

import com.techkritika.chicken99_backend.dto.MenuItemDto;
import java.util.List;
import java.util.Map;

public interface MenuService {

    List<MenuItemDto> getMenuByRestaurant(Long restaurantId);
    
    Map<String, List<MenuItemDto>> getMenuByRestaurantGroupedByCategory(Long restaurantId);
    
    Map<String, Map<String, List<MenuItemDto>>> getMenuByRestaurantGroupedByCategoryAndSubcategory(Long restaurantId);
    
    MenuItemDto getMenuItem(Long restaurantId, Long itemId);
    
    MenuItemDto addMenuItem(Long restaurantId, MenuItemDto dto);
    
    MenuItemDto updateMenuItem(Long restaurantId, Long itemId, MenuItemDto dto);
    
    void deleteMenuItem(Long restaurantId, Long itemId);
}