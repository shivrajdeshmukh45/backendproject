package com.techkritika.chicken99_backend.service.impl;
import com.techkritika.chicken99_backend.dto.MenuItemDto;
import com.techkritika.chicken99_backend.entity.MenuItem;
import com.techkritika.chicken99_backend.entity.Restaurant;
import com.techkritika.chicken99_backend.exception.ResourceNotFoundException;
import com.techkritika.chicken99_backend.repository.RestaurantRepository;
import com.techkritika.chicken99_backend.repository.MenuRepository;
import com.techkritika.chicken99_backend.service.MenuService;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    private MenuRepository menuRepository;

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private ModelMapper modelMapper;

    // Get all menu items for a restaurant
    public List<MenuItemDto> getMenuByRestaurant(Long restaurantId) {
        validateRestaurant(restaurantId);
        return menuRepository.findByRestaurantId(restaurantId)
                .stream()
                .sorted((item1, item2) -> {
                    // Sort by menuItemNumber if available, otherwise by id
                    if (item1.getMenuItemNumber() != null && item2.getMenuItemNumber() != null) {
                        return item1.getMenuItemNumber().compareTo(item2.getMenuItemNumber());
                    } else if (item1.getMenuItemNumber() != null) {
                        return -1;
                    } else if (item2.getMenuItemNumber() != null) {
                        return 1;
                    } else {
                        return item1.getId().compareTo(item2.getId());
                    }
                })
                .map(menuItem -> modelMapper.map(menuItem, MenuItemDto.class))
                .collect(Collectors.toList());
    }

    // Get menu items grouped by category for frontend display
    public Map<String, List<MenuItemDto>> getMenuByRestaurantGroupedByCategory(Long restaurantId) {
        validateRestaurant(restaurantId);
        return menuRepository.findByRestaurantId(restaurantId)
                .stream()
                .sorted((item1, item2) -> {
                    // Sort by menuItemNumber if available, otherwise by id
                    if (item1.getMenuItemNumber() != null && item2.getMenuItemNumber() != null) {
                        return item1.getMenuItemNumber().compareTo(item2.getMenuItemNumber());
                    } else if (item1.getMenuItemNumber() != null) {
                        return -1;
                    } else if (item2.getMenuItemNumber() != null) {
                        return 1;
                    } else {
                        return item1.getId().compareTo(item2.getId());
                    }
                })
                .map(menuItem -> modelMapper.map(menuItem, MenuItemDto.class))
                .collect(Collectors.groupingBy(MenuItemDto::getCategory));
    }

    // Get menu items grouped by category and subcategory for detailed frontend display
    public Map<String, Map<String, List<MenuItemDto>>> getMenuByRestaurantGroupedByCategoryAndSubcategory(Long restaurantId) {
        validateRestaurant(restaurantId);
        return menuRepository.findByRestaurantId(restaurantId)
                .stream()
                .sorted((item1, item2) -> {
                    // Sort by menuItemNumber if available, otherwise by id
                    if (item1.getMenuItemNumber() != null && item2.getMenuItemNumber() != null) {
                        return item1.getMenuItemNumber().compareTo(item2.getMenuItemNumber());
                    } else if (item1.getMenuItemNumber() != null) {
                        return -1;
                    } else if (item2.getMenuItemNumber() != null) {
                        return 1;
                    } else {
                        return item1.getId().compareTo(item2.getId());
                    }
                })
                .map(menuItem -> modelMapper.map(menuItem, MenuItemDto.class))
                .collect(Collectors.groupingBy(
                    MenuItemDto::getCategory,
                    Collectors.groupingBy(dto -> dto.getSubcategory() != null ? dto.getSubcategory() : "General")
                ));
    }

    // Get single menu item
    public MenuItemDto getMenuItem(Long restaurantId, Long itemId) {
        validateRestaurant(restaurantId);
        MenuItem menuItem = menuRepository.findById(itemId)
                .filter(item -> item.getRestaurant().getId().equals(restaurantId))
                .orElseThrow(() -> new ResourceNotFoundException("Menu item not found"));

        return modelMapper.map(menuItem, MenuItemDto.class);
    }

    // Add menu item
    public MenuItemDto addMenuItem(Long restaurantId, MenuItemDto dto) {
        Restaurant restaurant = validateRestaurant(restaurantId);
        MenuItem menuItem = modelMapper.map(dto, MenuItem.class);
        menuItem.setRestaurant(restaurant);
        
        // Auto-assign sequential menu item number within the restaurant
        Integer nextMenuItemNumber = menuRepository.findMaxMenuItemNumberByRestaurantId(restaurantId) + 1;
        menuItem.setMenuItemNumber(nextMenuItemNumber);
        
        MenuItem savedItem = menuRepository.save(menuItem);
        return modelMapper.map(savedItem, MenuItemDto.class);
    }

    // Update menu item
    public MenuItemDto updateMenuItem(Long restaurantId, Long itemId, MenuItemDto dto) {
        validateRestaurant(restaurantId);
        MenuItem existingItem = menuRepository.findById(itemId)
                .orElseThrow(() -> new ResourceNotFoundException("Menu item not found"));

        existingItem.setName(dto.getName());
        existingItem.setDescription(dto.getDescription());
        existingItem.setPrice(dto.getPrice());
        existingItem.setCategory(dto.getCategory());
        existingItem.setSubcategory(dto.getSubcategory());
        existingItem.setIsAvailable(dto.getIsAvailable());
        existingItem.setImageUrl(dto.getImageUrl());

        MenuItem updated = menuRepository.save(existingItem);
        return modelMapper.map(updated, MenuItemDto.class);
    }

    // Delete menu item
    public void deleteMenuItem(Long restaurantId, Long itemId) {
        validateRestaurant(restaurantId);
        MenuItem menuItem = menuRepository.findById(itemId)
                .orElseThrow(() -> new ResourceNotFoundException("Menu item not found"));
        menuRepository.delete(menuItem);
    }

    private Restaurant validateRestaurant(Long restaurantId) {
        return restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new ResourceNotFoundException("Restaurant not found"));
    }
}
