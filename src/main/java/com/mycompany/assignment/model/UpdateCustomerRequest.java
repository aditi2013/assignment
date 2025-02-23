package com.mycompany.assignment.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class UpdateCustomerRequest {
    private final UUID id;
    private final String firstName;
    private final String middleName;
    private final String lastName;
    private final String phone;
    private final String email;
}
