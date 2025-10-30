package com.techkritika.chicken99_backend.controller;

import com.techkritika.chicken99_backend.dto.CustomerDto;
import com.techkritika.chicken99_backend.entity.Customer;
import com.techkritika.chicken99_backend.service.CustomerService;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@RestController
@AllArgsConstructor
@RequestMapping("/customers")
@CrossOrigin(origins = {"http://localhost:3001", "http://localhost:3000"})
public class CustomerController {

    private final CustomerService customerService;

    // POST - Create a new customer
    @PostMapping
    public ResponseEntity<Customer> createCustomer(@RequestBody CustomerDto customerDto) {
        Customer createdCustomer = customerService.createCustomer(customerDto);
        return new ResponseEntity<>(createdCustomer, HttpStatus.CREATED);
    }

    // GET - Get all customers
    @GetMapping
    public ResponseEntity<List<CustomerDto>> getAllCustomers() {
        List<CustomerDto> customers = customerService.getAllCustomers();
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }

    // GET - Get customer by ID
    @GetMapping("/{id}")
    public ResponseEntity<CustomerDto> getCustomerById(@PathVariable Long id) {
        CustomerDto customer = customerService.getCustomerById(id);
        return new ResponseEntity<>(customer, HttpStatus.OK);
    }

    // PUT - Update customer completely
    @PutMapping("/{id}")
    public ResponseEntity<CustomerDto> updateCustomer(@PathVariable Long id, @RequestBody CustomerDto customerDto) {
        CustomerDto updatedCustomer = customerService.updateCustomer(id, customerDto);
        return new ResponseEntity<>(updatedCustomer, HttpStatus.OK);
    }

    // PATCH - Partial update of customer
    @PatchMapping("/{id}")
    public ResponseEntity<Object> updatePartialCustomer(@PathVariable Long id, @RequestBody Map<String, Object> updates) {
        Object updatedCustomer = customerService.updatePartialCustomer(id, updates);
        return new ResponseEntity<>(updatedCustomer, HttpStatus.OK);
    }

    // DELETE - Delete customer
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable Long id) {
        customerService.deleteCustomer(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
