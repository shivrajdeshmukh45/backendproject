package com.techkritika.chicken99_backend.service;
import java.util.*;

import com.techkritika.chicken99_backend.entity.Restaurant;
import com.techkritika.chicken99_backend.dto.AddRestaurantDto;
import com.techkritika.chicken99_backend.dto.RestaurantDto;

public interface RestaurantService {
    Restaurant createRestaurant(AddRestaurantDto addRestaurantDto);
    List <RestaurantDto> getAllRestaurants();
    RestaurantDto getRestaurantById(Long id);
    RestaurantDto updateRestaurant(Long id, AddRestaurantDto addRestaurantDto);
    void deleteRestaurantById(Long id);
    Object updatePartialRestaurant(Long id, Map<String, Object> updates);
}
