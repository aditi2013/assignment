package com.mycompany.assignment.model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import org.springframework.validation.annotation.Validated;

import java.util.UUID;

@Getter
public class UpdateCustomerRequest extends CreateCustomerRequest {
    @NotNull
    private final UUID id;

    public UpdateCustomerRequest(UUID id, String firstName, String middleName,
                                 String lastName, String phone, String email) {
        super(firstName, middleName, lastName, phone, email);
        this.id = id;
    }
}
