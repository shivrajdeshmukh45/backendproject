package com.techkritika.chicken99_backend.repository;
import com.techkritika.chicken99_backend.entity.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RestaurantRepository  extends JpaRepository<Restaurant, Long>  {
    
}
