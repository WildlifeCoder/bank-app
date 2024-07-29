package com.accenture.customers.repository;

import com.accenture.customers.dto.CustomerDto;
import com.accenture.customers.entity.Customer;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    Optional<Customer> findByDocument(String document);

    Optional<Customer> findByEmail(String email);

    // List<Customer> findAllCustomers();



    @Modifying
    @Transactional
    void deleteCustomerByDocument(String document);

    @Modifying
    @Transactional
    void deleteCustomerByEmail(String document);



}
