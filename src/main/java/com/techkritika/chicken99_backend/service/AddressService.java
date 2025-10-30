package com.techkritika.chicken99_backend.service;

import com.techkritika.chicken99_backend.dto.AddressDto;

import java.util.List;

public interface AddressService {
    
    // Add a new address for a customer
    AddressDto addAddress(Long customerId, AddressDto addressDto);
    
    // Get all active addresses for a customer
    List<AddressDto> getCustomerAddresses(Long customerId);
    
    // Get a specific address by ID
    AddressDto getAddressById(Long addressId);
    
    // Update an address
    AddressDto updateAddress(Long addressId, AddressDto addressDto);
    
    // Delete an address (soft delete)
    void deleteAddress(Long addressId);
    
    // Set an address as default
    AddressDto setDefaultAddress(Long customerId, Long addressId);
    
    // Get default address for a customer
    AddressDto getDefaultAddress(Long customerId);
    
    // Get addresses by type for a customer
    List<AddressDto> getAddressesByType(Long customerId, String addressType);
}