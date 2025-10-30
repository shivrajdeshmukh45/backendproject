package com.techkritika.chicken99_backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.techkritika.chicken99_backend.entity.Order;
import java.util.List;


public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByCustomerIdOrderByOrderDateDesc(Long customerId);
}
