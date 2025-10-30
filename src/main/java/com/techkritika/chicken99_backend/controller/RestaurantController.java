package com.techkritika.chicken99_backend.controller;
import com.techkritika.chicken99_backend.dto.AddRestaurantDto;
import com.techkritika.chicken99_backend.dto.RestaurantDto;
import com.techkritika.chicken99_backend.entity.Restaurant;
import com.techkritika.chicken99_backend.service.RestaurantService;

import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;



// @CrossOrigin(origins = "http://localhost:3000")
@RestController
@AllArgsConstructor
// @RequestMapping("/api/restaurants")
public class RestaurantController {

    private final RestaurantService restaurantService;
    private final ModelMapper modelMapper;




        @GetMapping("/restaurants")
    public ResponseEntity<List<RestaurantDto>> getAllRestaurants(){
        return ResponseEntity.ok(restaurantService.getAllRestaurants());
    }

    @GetMapping("/restaurants/{id}")
    public ResponseEntity<RestaurantDto> getRestaurantById(@PathVariable Long id) {
        return ResponseEntity.ok(restaurantService.getRestaurantById(id));
    }
    

    @PostMapping("/restaurants")
    public ResponseEntity<RestaurantDto> createRestaurant(@RequestBody AddRestaurantDto addRestaurantDto) {
        Restaurant restaurant = restaurantService.createRestaurant(addRestaurantDto);
        RestaurantDto restaurantDto = modelMapper.map(restaurant, RestaurantDto.class);
        return ResponseEntity.status(HttpStatus.CREATED).body(restaurantDto);
    }
    

    @PutMapping("/restaurants/{id}")
    public ResponseEntity<RestaurantDto> updateRestaurant(@PathVariable Long id, @RequestBody AddRestaurantDto addRestaurantDto) {
        return ResponseEntity.ok(restaurantService.updateRestaurant(id, addRestaurantDto));
    }

    @DeleteMapping("/restaurants/{id}")

    public ResponseEntity<Void> deleteRestaurant(@PathVariable Long id) {
        restaurantService.deleteRestaurantById(id);
        return ResponseEntity.noContent().build();
    }


    @PatchMapping("/restaurants/{id}")
    public ResponseEntity<Object> updatePartialRestaurant(@PathVariable Long id, @RequestBody Map<String, Object> updates) {
        return ResponseEntity.ok(restaurantService.updatePartialRestaurant(id, updates));
    }

       

}

