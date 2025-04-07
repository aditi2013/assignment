package com.mycompany.assignment.repository;

import com.mycompany.assignment.entity.Customer;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
public interface CustomerRepository extends JpaRepository<Customer, UUID> {
    @Override
    @EntityGraph(attributePaths = "address")
    @NonNull
    List<Customer> findAll();

    @Override
    @EntityGraph(attributePaths = "address")
    @NonNull
    List<Customer> findAllById(@NonNull Iterable<UUID> uuids);

    @Override
    @EntityGraph(attributePaths = "address")
    @NonNull
    Optional<Customer> findById(@NonNull UUID id);
}
