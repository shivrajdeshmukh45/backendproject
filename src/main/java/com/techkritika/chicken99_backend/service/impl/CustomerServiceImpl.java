package com.techkritika.chicken99_backend.service.impl;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import java.util.Optional;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.techkritika.chicken99_backend.dto.CustomerDto;
import com.techkritika.chicken99_backend.entity.Customer;
import com.techkritika.chicken99_backend.exception.ResourceNotFoundException;
import com.techkritika.chicken99_backend.repository.CustomerRepository;
import com.techkritika.chicken99_backend.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ModelMapper modelMapper;


    //for post api

    @Override
    public Customer createCustomer(CustomerDto customerDto) {
        Customer customer = modelMapper.map(customerDto, Customer.class);
        return customerRepository.save(customer);
    }





    //for get all api
    @Override
    public List<CustomerDto> getAllCustomers() {
        List<Customer>customers=customerRepository.findAll();
        return customers.stream()
                        .map(customer->modelMapper.map(customer,CustomerDto.class))
                        .collect(Collectors.toList());
         
       
    }


   
      
        //get Customer by id
    @Override
    public CustomerDto getCustomerById(Long id) {
        validateCustomer(id);
        Optional<Customer> customer = customerRepository.findById(id);

        if(customer.isPresent()){
            return modelMapper.map(customer.get(),CustomerDto.class);
        }
        else{
            throw new RuntimeException("Customer not found with id: "+id);
        }
    }


    //For updating the customer 

    @Override
    public CustomerDto updateCustomer(Long id, CustomerDto customerDto) {
        Customer existingCustomer = validateCustomer(id);
        
        // Map the DTO to the existing customer (this will update the fields)
        modelMapper.map(customerDto, existingCustomer);
        existingCustomer.setId(id); // Ensure that id remains the same
        
        Customer updatedCustomer = customerRepository.save(existingCustomer);
        return modelMapper.map(updatedCustomer, CustomerDto.class);
    }



    //Delete the Customer
    @Override
    public void deleteCustomer(Long id) {
        Customer customer = validateCustomer(id);
        customerRepository.delete(customer);
    }



    //For partial update of customer

    @Override
    public Object updatePartialCustomer(Long id, Map<String, Object> updates) {
        Customer existingCustomer = validateCustomer(id);
        
        // Apply partial updates
        updates.forEach((key, value) -> {
            switch (key) {
                case "name":
                    existingCustomer.setName((String) value);
                    break;
                case "email":
                    existingCustomer.setEmail((String) value);
                    break;
                case "phoneNumber":
                    existingCustomer.setPhoneNumber((String) value);
                    break;
                // Add more fields as needed
                default:
                    throw new IllegalArgumentException("Field " + key + " is not updatable");
            }
        });
        
        Customer updatedCustomer = customerRepository.save(existingCustomer);
        return modelMapper.map(updatedCustomer, CustomerDto.class);
    }

    
    private Customer validateCustomer(Long customerId) {
        return customerRepository.findById(customerId)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found"));
    }



}
