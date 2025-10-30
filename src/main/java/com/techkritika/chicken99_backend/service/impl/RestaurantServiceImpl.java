package com.techkritika.chicken99_backend.service.impl;

import java.time.LocalTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;

import org.springframework.stereotype.Service;

import com.techkritika.chicken99_backend.dto.AddRestaurantDto;
import com.techkritika.chicken99_backend.dto.RestaurantDto;
import com.techkritika.chicken99_backend.entity.Restaurant;
import com.techkritika.chicken99_backend.repository.RestaurantRepository;
import com.techkritika.chicken99_backend.service.RestaurantService;

@Service
public class RestaurantServiceImpl implements RestaurantService {

    private final RestaurantRepository restaurantRepository;
    private final ModelMapper modelMapper;

   
    public RestaurantServiceImpl(RestaurantRepository restaurantRepository, ModelMapper modelMapper) {
        this.restaurantRepository = restaurantRepository;
        this.modelMapper = modelMapper;
    }






    @Override
    public List<RestaurantDto> getAllRestaurants() {
        List<Restaurant> restaurants = restaurantRepository.findAll();
        
        // Map Restaurant entities to RestaurantDto
        return restaurants.stream()
                .map(restaurant -> modelMapper.map(restaurant, RestaurantDto.class))
                .collect(Collectors.toList());
    }



    @Override
    public RestaurantDto getRestaurantById(Long id) {
        Optional<Restaurant> restaurant = restaurantRepository.findById(id);
        
        if (restaurant.isPresent()) {
            return modelMapper.map(restaurant.get(), RestaurantDto.class);
        } else {
            throw new RuntimeException("Restaurant not found with id: " + id);
        }
    }

    @Override
    public RestaurantDto updateRestaurant(Long id, AddRestaurantDto addRestaurantDto) {
        Restaurant existingRestaurant = restaurantRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Restaurant not found with id: " + id));
        
        // Update the existing restaurant with new data
        modelMapper.map(addRestaurantDto, existingRestaurant);
        existingRestaurant.setId(id); // Ensure the ID remains the same
        
        Restaurant updatedRestaurant = restaurantRepository.save(existingRestaurant);
        return modelMapper.map(updatedRestaurant, RestaurantDto.class);
    }






    @Override
    public Restaurant createRestaurant(AddRestaurantDto addRestaurant) {
        Restaurant newRestaurant = modelMapper.map(addRestaurant, Restaurant.class);
        return restaurantRepository.save(newRestaurant);
    }






    @Override
    public void deleteRestaurantById(Long id) {
       Restaurant restaurant = restaurantRepository.findById(id)
        .orElseThrow(() -> new IllegalArgumentException("Restaurant not found with ID " + id));
    restaurantRepository.delete(restaurant);
    }






    @Override
    public Object updatePartialRestaurant(Long id, Map<String, Object> updates) {
        Restaurant restaurant = restaurantRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Restaurant not found with ID " + id));

                updates.forEach((field,value) ->{
                    switch(field){
                        case "name":
                            restaurant.setName((String) value);
                            break;
                        case "phoneNumber":
                            restaurant.setPhoneNumber((String) value);
                            break;
                        case "email":
                            restaurant.setEmail((String) value);
                            break;
                        case "description":
                            restaurant.setDescription((String) value);
                            break;
                        case "category":
                            restaurant.setCategory((String) value);
                            break;
                        case "tags":
                            restaurant.setTags((String[]) value);
                            break;
                        case "logoUrl":
                            restaurant.setLogoUrl((String) value);
                            break;
                        case "bannerUrl":
                            restaurant.setBannerUrl((String) value);
                            break;
                        case "address":
                            restaurant.setAddress((String) value);
                            break;
                        case "city":
                            restaurant.setCity((String) value);
                            break;
                        case "state":
                            restaurant.setState((String) value);
                            break;
                        case "pincode":
                            restaurant.setPincode((String) value);
                            break;
                        case "openingTime":
                            restaurant.setOpeningTime(LocalTime.parse((String) value));
                            break;
                        case "closingTime":
                            restaurant.setClosingTime(LocalTime.parse((String) value));
                            break;
                        case "deliveryTime":
                            restaurant.setDeliveryTime((Integer) value);
                            break;
                        case "minOrderValue":
                            restaurant.setMinOrderValue((Double) value);
                            break;
                        case "isPureVeg":
                            restaurant.setIsPureVeg((Boolean) value);
                            break;
                        case "isFeatured":
                            restaurant.setIsFeatured((Boolean) value);
                            break;
                        case "deliveryAvailable":
                            restaurant.setDeliveryAvailable((Boolean) value);
                            break;
                        case "takeawayAvailable":
                            restaurant.setTakeawayAvailable((Boolean) value);
                            break;
                        case "deliveryCharge":
                            restaurant.setDeliveryCharge((Double) value);
                            break;
                        case "averageRating":
                            restaurant.setAverageRating((Double) value);
                            break;
                        case "reviewCount":
                            restaurant.setReviewCount((Integer) value);
                            break;
                        case "paymentModes":
                            restaurant.setPaymentModes((String[]) value);
                            break;
                        case "status":
                            restaurant.setStatus((String) value);
                            break;
                        case "approvedByAdmin":
                            restaurant.setApprovedByAdmin((Boolean) value);
                            break;
                        default:

                    }
                });
                Restaurant updatedRestaurant = restaurantRepository.save(restaurant);
                return modelMapper.map(updatedRestaurant, RestaurantDto.class);
    }






}
