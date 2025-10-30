package com.techkritika.chicken99_backend.controller;  

import com.techkritika.chicken99_backend.dto.MenuItemDto;
import com.techkritika.chicken99_backend.service.MenuService;

import lombok.AllArgsConstructor;

import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping("/restaurants/{restaurantId}/menu")
public class MenuController {

    private final MenuService menuService;

    @GetMapping
    public List<MenuItemDto> getMenu(@PathVariable Long restaurantId) {
        return menuService.getMenuByRestaurant(restaurantId);
    }

    @GetMapping("/grouped")
    public Map<String, List<MenuItemDto>> getMenuGroupedByCategory(@PathVariable Long restaurantId) {
        return menuService.getMenuByRestaurantGroupedByCategory(restaurantId);
    }

    @GetMapping("/grouped-detailed")
    public Map<String, Map<String, List<MenuItemDto>>> getMenuGroupedByCategoryAndSubcategory(@PathVariable Long restaurantId) {
        return menuService.getMenuByRestaurantGroupedByCategoryAndSubcategory(restaurantId);
    }

    @GetMapping("/{itemId}")
    public MenuItemDto getMenuItem(@PathVariable Long restaurantId, @PathVariable Long itemId) {
        return menuService.getMenuItem(restaurantId, itemId);
    }

    @PostMapping
    public MenuItemDto addMenuItem(@PathVariable Long restaurantId, @RequestBody MenuItemDto dto) {
        return menuService.addMenuItem(restaurantId, dto);
    }

    @PutMapping("/{itemId}")
    public MenuItemDto updateMenuItem(@PathVariable Long restaurantId, @PathVariable Long itemId,
                                      @RequestBody MenuItemDto dto) {
        return menuService.updateMenuItem(restaurantId, itemId, dto);
    }

    @DeleteMapping("/{itemId}")
    public void deleteMenuItem(@PathVariable Long restaurantId, @PathVariable Long itemId) {
        menuService.deleteMenuItem(restaurantId, itemId);
    }
}
