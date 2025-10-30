package com.techkritika.chicken99_backend.repository;

import com.techkritika.chicken99_backend.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long>  {
    
}
