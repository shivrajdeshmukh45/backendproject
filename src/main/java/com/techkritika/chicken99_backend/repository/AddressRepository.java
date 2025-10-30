package com.techkritika.chicken99_backend.repository;

import com.techkritika.chicken99_backend.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface AddressRepository extends JpaRepository<Address, Long> {
    
    // Find all addresses for a specific customer
    List<Address> findByCustomerIdAndIsActiveTrue(Long customerId);
    
    // Find all addresses for a customer (including inactive)
    List<Address> findByCustomerId(Long customerId);
    
    // Find default address for a customer
    Optional<Address> findByCustomerIdAndIsDefaultTrueAndIsActiveTrue(Long customerId);
    
    // Find addresses by type for a customer
    List<Address> findByCustomerIdAndAddressTypeAndIsActiveTrue(Long customerId, String addressType);
    
    // Count active addresses for a customer
    @Query("SELECT COUNT(a) FROM Address a WHERE a.customer.id = :customerId AND a.isActive = true")
    Long countActiveAddressesByCustomerId(@Param("customerId") Long customerId);
}