package com.mycompany.assignment.service;

import com.mycompany.assignment.entity.Customer;
import com.mycompany.assignment.model.CreateCustomerRequest;
import com.mycompany.assignment.repository.CustomerRepository;
import jakarta.validation.ConstraintViolationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class CustomerServiceImplTest {
    @InjectMocks
    private CustomerServiceImpl customerService;

    @Mock
    private CustomerRepository customerRepository;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testFindCustomerById_whenSearchedValidCustomer_thenCustomerShouldBeFound() {
        UUID customerId = UUID.randomUUID();
        Customer customer = new Customer(customerId, "John", "P", "Doe",
                "john.doe@mycompany.com", "9876543210", null);

        when(customerRepository.findById(customerId)).thenReturn(Optional.of(customer));
        Customer found = customerService.findById(customerId).orElse(null);
        assert found != null;
        assertEquals("John", found.getFirstName());
        assertEquals("Doe", found.getLastName());
        assertEquals("john.doe@mycompany.com", found.getEmail());
        assertEquals("9876543210", found.getPhone());
    }

    @Test
    public void testFindCustomerById_whenSearchedInvalidCustomer_thenCustomerShouldNotBeFound() {
        UUID customerId = UUID.randomUUID();
        Customer customer = new Customer(customerId, "John", "P", "Doe",
                "john.doe@mycompany.com", "9876543210", null);

        when(customerRepository.findById(UUID.randomUUID())).thenReturn(Optional.empty());
        Customer found = customerService.findById(customerId).orElse(null);
        assertNull(found);
    }

    @Test
    public void testCreate_whenValidRequestProvided_thenCustomerShouldBeCreated() {
        UUID customerId = UUID.randomUUID();
        CreateCustomerRequest createCustomerRequest = new CreateCustomerRequest("John", "P", "Doe",
                "john.doe@mycompany.com", "9876543210", null);

        Customer customer = new Customer(customerId, "John", "P", "Doe",
                "john.doe@mycompany.com", "9876543210", null);

        when(customerRepository.save(any(Customer.class))).thenReturn(customer);
        Customer newCustomer = customerService.create(createCustomerRequest);
        assertNotNull(newCustomer);
        assertEquals("John", newCustomer.getFirstName());
        assertEquals("Doe", newCustomer.getLastName());
        assertEquals("john.doe@mycompany.com", newCustomer.getEmail());
    }

    @Test
    public void testCreate_whenInvalidRequestProvidedWithDuplicateEmail_thenExceptionShouldBeThrown() {
        UUID customerId = UUID.randomUUID();
        CreateCustomerRequest createCustomerRequest = new CreateCustomerRequest("John", "P", "Doe",
                "john.doe@mycompany.com", "9876543210", null);

        when(customerRepository.save(any(Customer.class))).thenThrow(
                new ConstraintViolationException("Duplicate entry 'john.doe@mycompany.com' for key 'customer.customer_email_uk'", Set.of()));
        //Customer newCustomer = customerService.create(createCustomerRequest);
        ConstraintViolationException thrownException = assertThrows(ConstraintViolationException.class, () -> {
            customerService.create(createCustomerRequest);
        });
        assertEquals("Duplicate entry 'john.doe@mycompany.com' for key 'customer.customer_email_uk'", thrownException.getMessage());
    }
}
