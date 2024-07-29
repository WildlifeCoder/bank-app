package com.accenture.customers.services;

import com.accenture.customers.dto.CustomerDto;

import java.util.List;

public interface ICustomerService {

    void createCustomer(CustomerDto customerDto);

    List<CustomerDto> fetchAllCustomers();
    CustomerDto fetchCustomerByDocument(String document);

    CustomerDto fetchCustomerByEmail(String email);

    CustomerDto updateCustomer(CustomerDto customer);

    void deleteByDocument(String document);

    void deleteByEmail(String email);

}
