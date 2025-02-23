package com.mycompany.assignment.controller;

import com.mycompany.assignment.entity.Customer;
import com.mycompany.assignment.model.CreateCustomerRequest;
import com.mycompany.assignment.model.UpdateCustomerRequest;
import com.mycompany.assignment.service.CustomerService;
import com.mycompany.assignment.service.CustomerServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/api/customer")
public class CustomerController {
    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerServiceImpl customerService) {
        this.customerService = customerService;
    }

    /**
     * Enpoint to retrieve all customers
     *
     * @return all customers available in system
     */
    @GetMapping
    public ResponseEntity<List<Customer>> getAllCustomer() {
        return new ResponseEntity<>(customerService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable UUID id) {
        return customerService.findById(id).map(customer -> new ResponseEntity<>(customer, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<?> createCustomer(@RequestBody CreateCustomerRequest createCustomerRequest) {
        if (createCustomerRequest == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        try {
            Customer savedCustomer = customerService.create(createCustomerRequest);
            return new ResponseEntity<>(savedCustomer, HttpStatus.CREATED);
        } catch (Exception e) {
//            if (e.getMessage().contains("constraint [customer.customer_email_uk]")) {
//                return ResponseEntity.badRequest().body("Duplicate customer email");
//            }
            log.error(e.getMessage(), e);
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable UUID id,
                                                   @RequestBody UpdateCustomerRequest updateCustomerRequest) {
        if (!id.equals(updateCustomerRequest.getId())) {
            return ResponseEntity.badRequest().build();
        }
        Customer modifiedCustomer = customerService.update(updateCustomerRequest);

        if (modifiedCustomer != null) {
            return new ResponseEntity<>(modifiedCustomer, HttpStatus.OK);
        } else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable UUID id) {
        return customerService.findById(id).map(customer -> {
            customerService.delete(id);
            return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
        }).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
