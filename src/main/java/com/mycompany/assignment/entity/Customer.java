package com.mycompany.assignment.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "customer", uniqueConstraints = {@UniqueConstraint(name = "${email.unique.constraint.name}", columnNames = {"email"})})
@Entity
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(nullable = false)
    private String firstName;
    private String middleName;
    @Column(nullable = false)
    private String lastName;
    @Email
    @Column(unique = true)
    private String email;
    @Column(nullable = false)
    private String phone;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER/*, targetEntity = Address.class*/)
    private List<Address> address;
}
