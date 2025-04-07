package com.mycompany.assignment.repository;

import com.mycompany.assignment.entity.Customer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class CustomerRepositoryTest {
    @Autowired
    private CustomerRepository customerRepository;

    private Customer testCustomer;

    @BeforeEach
    public void setup() {
        testCustomer = new Customer(null, "John", "P", "Doe",
                "john.doe@mycompany.com", "9876543210", null);
    }

    @AfterEach
    public void cleanUp() {
        customerRepository.deleteById(testCustomer.getId());
    }

    @Test
    public void testSaveCustomer() {
        Customer savedCustomer = customerRepository.save(testCustomer);
        assertNotNull(savedCustomer);
        assertEquals("John", savedCustomer.getFirstName());
        assertEquals("Doe", savedCustomer.getLastName());
        assertEquals("P", savedCustomer.getMiddleName());
        assertEquals("john.doe@mycompany.com", savedCustomer.getEmail());
        assertEquals("9876543210", savedCustomer.getPhone());
    }

    @Test
    public void testGetCustomer() {
        Customer customer = customerRepository.save(testCustomer);
        Customer fetchedCustomer = customerRepository.findById(customer.getId()).orElse(null);
        assertNotNull(fetchedCustomer);
        assertEquals(customer.getId(), fetchedCustomer.getId());
        assertEquals("John", fetchedCustomer.getFirstName());
        assertEquals("Doe", fetchedCustomer.getLastName());
        assertEquals("P", fetchedCustomer.getMiddleName());
        assertEquals("john.doe@mycompany.com", fetchedCustomer.getEmail());
    }

    @Test
    public void testGetListOfCustomers() {
        Customer savedTestCustomer = customerRepository.save(testCustomer);
        Customer newCustomer = customerRepository.save(new Customer(null, "Peter", "S", "Smith",
                "peter.smith@mycompany.com", "8769762123", null));
        List<Customer> customers = customerRepository.findAll();
        assertNotNull(customers);
        assertTrue(customers.contains(savedTestCustomer));
        assertTrue(customers.contains(newCustomer));
        customerRepository.deleteById(newCustomer.getId());
    }

    @Test
    public void testUpdateCustomer() {
        Customer customer = customerRepository.save(testCustomer);
        customer.setFirstName("Peter");
        customer.setLastName("Smith");
        customer.setMiddleName("S");
        customer.setEmail("peter.smith@mycompany.com");
        customerRepository.save(customer);
        Customer updatedCustomer = customerRepository.findById(customer.getId()).orElse(null);
        assertNotNull(updatedCustomer);
        assertEquals("Peter", updatedCustomer.getFirstName());
        assertEquals("Smith", updatedCustomer.getLastName());
        assertEquals("S", updatedCustomer.getMiddleName());
        assertEquals("peter.smith@mycompany.com", updatedCustomer.getEmail());
    }

    @Test
    public void testDeleteEmployee() {
        Customer customer = customerRepository.save(testCustomer);
        customerRepository.deleteById(customer.getId());
        Customer deletedCustomer = customerRepository.findById(customer.getId()).orElse(null);
        assertNull(deletedCustomer);
    }
}
