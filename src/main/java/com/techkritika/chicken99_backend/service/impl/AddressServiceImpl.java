package com.techkritika.chicken99_backend.service.impl;

import com.techkritika.chicken99_backend.dto.AddressDto;
import com.techkritika.chicken99_backend.entity.Address;
import com.techkritika.chicken99_backend.entity.Customer;
import com.techkritika.chicken99_backend.exception.ResourceNotFoundException;
import com.techkritika.chicken99_backend.repository.AddressRepository;
import com.techkritika.chicken99_backend.repository.CustomerRepository;
import com.techkritika.chicken99_backend.service.AddressService;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AddressServiceImpl implements AddressService {

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    @Transactional
    public AddressDto addAddress(Long customerId, AddressDto addressDto) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found"));

        Address address = modelMapper.map(addressDto, Address.class);
        address.setCustomer(customer);
        address.setIsActive(true);

        // If this is marked as default, remove default from other addresses
        if (addressDto.getIsDefault() != null && addressDto.getIsDefault()) {
            setOtherAddressesAsNonDefault(customerId);
        }

        Address savedAddress = addressRepository.save(address);
        return modelMapper.map(savedAddress, AddressDto.class);
    }

    @Override
    public List<AddressDto> getCustomerAddresses(Long customerId) {
        List<Address> addresses = addressRepository.findByCustomerIdAndIsActiveTrue(customerId);
        return addresses.stream()
                .map(address -> modelMapper.map(address, AddressDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public AddressDto getAddressById(Long addressId) {
        Address address = addressRepository.findById(addressId)
                .orElseThrow(() -> new ResourceNotFoundException("Address not found"));
        return modelMapper.map(address, AddressDto.class);
    }

    @Override
    @Transactional
    public AddressDto updateAddress(Long addressId, AddressDto addressDto) {
        Address existingAddress = addressRepository.findById(addressId)
                .orElseThrow(() -> new ResourceNotFoundException("Address not found"));

        // Update fields
        existingAddress.setAddressType(addressDto.getAddressType());
        existingAddress.setStreet(addressDto.getStreet());
        existingAddress.setApartment(addressDto.getApartment());
        existingAddress.setCity(addressDto.getCity());
        existingAddress.setState(addressDto.getState());
        existingAddress.setZipCode(addressDto.getZipCode());
        existingAddress.setCountry(addressDto.getCountry());
        existingAddress.setLandmark(addressDto.getLandmark());

        // Handle default address change
        if (addressDto.getIsDefault() != null && addressDto.getIsDefault()) {
            setOtherAddressesAsNonDefault(existingAddress.getCustomer().getId());
            existingAddress.setIsDefault(true);
        }

        Address updatedAddress = addressRepository.save(existingAddress);
        return modelMapper.map(updatedAddress, AddressDto.class);
    }

    @Override
    @Transactional
    public void deleteAddress(Long addressId) {
        Address address = addressRepository.findById(addressId)
                .orElseThrow(() -> new ResourceNotFoundException("Address not found"));
        
        // Soft delete
        address.setIsActive(false);
        address.setIsDefault(false);
        addressRepository.save(address);
    }

    @Override
    @Transactional
    public AddressDto setDefaultAddress(Long customerId, Long addressId) {
        Address address = addressRepository.findById(addressId)
                .orElseThrow(() -> new ResourceNotFoundException("Address not found"));

        if (!address.getCustomer().getId().equals(customerId)) {
            throw new IllegalArgumentException("Address does not belong to this customer");
        }

        // Remove default from other addresses
        setOtherAddressesAsNonDefault(customerId);

        // Set this address as default
        address.setIsDefault(true);
        Address updatedAddress = addressRepository.save(address);
        
        return modelMapper.map(updatedAddress, AddressDto.class);
    }

    @Override
    public AddressDto getDefaultAddress(Long customerId) {
        Address defaultAddress = addressRepository.findByCustomerIdAndIsDefaultTrueAndIsActiveTrue(customerId)
                .orElseThrow(() -> new ResourceNotFoundException("No default address found for customer"));
        
        return modelMapper.map(defaultAddress, AddressDto.class);
    }

    @Override
    public List<AddressDto> getAddressesByType(Long customerId, String addressType) {
        List<Address> addresses = addressRepository.findByCustomerIdAndAddressTypeAndIsActiveTrue(customerId, addressType.toUpperCase());
        return addresses.stream()
                .map(address -> modelMapper.map(address, AddressDto.class))
                .collect(Collectors.toList());
    }

    // Helper method to set other addresses as non-default
    private void setOtherAddressesAsNonDefault(Long customerId) {
        List<Address> addresses = addressRepository.findByCustomerId(customerId);
        addresses.forEach(addr -> addr.setIsDefault(false));
        addressRepository.saveAll(addresses);
    }
}