package com.techkritika.chicken99_backend.service;
import com.techkritika.chicken99_backend.dto.CustomerDto;
import com.techkritika.chicken99_backend.entity.Customer;

import java.util.List;
import java.util.Map;



public interface CustomerService {

    Customer createCustomer( CustomerDto customer);
    CustomerDto getCustomerById(Long id);
    List<CustomerDto> getAllCustomers();
    void deleteCustomer(Long id);
    Object updatePartialCustomer(Long id, Map<String, Object> updates);
    CustomerDto updateCustomer(Long id, CustomerDto customerDto);

}
