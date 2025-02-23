package com.mycompany.assignment.service;

import com.mycompany.assignment.entity.Customer;
import com.mycompany.assignment.model.CreateCustomerRequest;
import com.mycompany.assignment.model.UpdateCustomerRequest;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CustomerService {
    List<Customer> findAll();

    Customer create(CreateCustomerRequest createCustomerRequest);

    Customer update(UpdateCustomerRequest updateCustomerRequest);

    void delete(UUID id);

    Optional<Customer> findById(UUID id);
}
