package com.mycompany.assignment.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "customer", uniqueConstraints = {@UniqueConstraint(name = "customer_email_uk", columnNames = {"email"})})
@Entity
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @NotNull
    private String firstName;
    private String middleName;
    @NotNull
    private String lastName;
    @Email
    @Column(name = "email", unique = true)
    private String email;
    private String phone;
}
