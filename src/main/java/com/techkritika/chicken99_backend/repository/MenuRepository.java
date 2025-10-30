package com.techkritika.chicken99_backend.repository;   

import com.techkritika.chicken99_backend.entity.MenuItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface MenuRepository extends JpaRepository<MenuItem, Long> {
    List<MenuItem> findByRestaurantId(Long restaurantId);
    
    @Query("SELECT COALESCE(MAX(m.menuItemNumber), 0) FROM MenuItem m WHERE m.restaurant.id = :restaurantId")
    Integer findMaxMenuItemNumberByRestaurantId(@Param("restaurantId") Long restaurantId);
}
