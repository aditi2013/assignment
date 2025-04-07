package com.mycompany.assignment.model;

import com.mycompany.assignment.entity.Address;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;
import java.util.UUID;

public record UpdateCustomerRequest(@NotNull UUID id,
                                    @NotNull @Size(min = 2, message = "First name should have at least 2 characters") String firstName,
                                    String middleName,
                                    @NotNull @Size(min = 2, message = "Last name should have at least 2 characters") String lastName,
                                    @NotNull @Size(min = 10, message = "Phone number should have at least 10 digits") String phone,
                                    @NotNull @Email String email, List<Address> address) {
}
