package com.techkritika.chicken99_backend.controller;

import com.techkritika.chicken99_backend.dto.AddressDto;
import com.techkritika.chicken99_backend.service.AddressService;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/customers/{customerId}/addresses")
public class AddressController {

    private final AddressService addressService;

    // POST - Add a new address for a customer
    @PostMapping
    public ResponseEntity<AddressDto> addAddress(@PathVariable Long customerId, @RequestBody AddressDto addressDto) {
        AddressDto createdAddress = addressService.addAddress(customerId, addressDto);
        return new ResponseEntity<>(createdAddress, HttpStatus.CREATED);
    }

    // GET - Get all addresses for a customer
    @GetMapping
    public ResponseEntity<List<AddressDto>> getCustomerAddresses(@PathVariable Long customerId) {
        List<AddressDto> addresses = addressService.getCustomerAddresses(customerId);
        return new ResponseEntity<>(addresses, HttpStatus.OK);
    }

    // GET - Get default address for a customer
    @GetMapping("/default")
    public ResponseEntity<AddressDto> getDefaultAddress(@PathVariable Long customerId) {
        AddressDto defaultAddress = addressService.getDefaultAddress(customerId);
        return new ResponseEntity<>(defaultAddress, HttpStatus.OK);
    }

    // GET - Get addresses by type for a customer
    @GetMapping("/type/{addressType}")
    public ResponseEntity<List<AddressDto>> getAddressesByType(@PathVariable Long customerId, @PathVariable String addressType) {
        List<AddressDto> addresses = addressService.getAddressesByType(customerId, addressType);
        return new ResponseEntity<>(addresses, HttpStatus.OK);
    }

    // PUT - Set an address as default
    @PutMapping("/{addressId}/default")
    public ResponseEntity<AddressDto> setDefaultAddress(@PathVariable Long customerId, @PathVariable Long addressId) {
        AddressDto defaultAddress = addressService.setDefaultAddress(customerId, addressId);
        return new ResponseEntity<>(defaultAddress, HttpStatus.OK);
    }
}

// Separate controller for direct address operations
@RestController
@AllArgsConstructor
@RequestMapping("/addresses")
class AddressManagementController {

    private final AddressService addressService;

    // GET - Get a specific address by ID
    @GetMapping("/{addressId}")
    public ResponseEntity<AddressDto> getAddress(@PathVariable Long addressId) {
        AddressDto address = addressService.getAddressById(addressId);
        return new ResponseEntity<>(address, HttpStatus.OK);
    }

    // PUT - Update an address
    @PutMapping("/{addressId}")
    public ResponseEntity<AddressDto> updateAddress(@PathVariable Long addressId, @RequestBody AddressDto addressDto) {
        AddressDto updatedAddress = addressService.updateAddress(addressId, addressDto);
        return new ResponseEntity<>(updatedAddress, HttpStatus.OK);
    }

    // DELETE - Delete an address
    @DeleteMapping("/{addressId}")
    public ResponseEntity<Void> deleteAddress(@PathVariable Long addressId) {
        addressService.deleteAddress(addressId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}