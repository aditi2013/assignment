package com.mycompany.assignment.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "address")
@Entity
//@Embeddable
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private Integer streetNumber;
    @Column(nullable = false)
    private String street;
    private String prefix;
    private String suffix;
    @Column(nullable = false)
    private String city;
    @Column(nullable = false)
    private String state;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "customer_id", columnDefinition = "uuid")
    private Customer customer;
}
