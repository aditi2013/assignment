package com.mycompany.assignment.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CreateCustomerRequest {
    private final String firstName;
    private final String middleName;
    private final String lastName;
    private final String phone;
    private final String email;
}
