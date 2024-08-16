package com.accenture.customers.services;

import com.accenture.customers.dto.CustomerDto;
import com.accenture.customers.dto.CustomerWithAccounts;

import java.util.List;

public interface ICustomerService {

    void createCustomer(CustomerDto customerDto);

    List<CustomerDto> fetchAllCustomers();

    CustomerWithAccounts fetchCustomersWithAccountsByDocument(String document);
    CustomerDto fetchCustomerByDocument(String document);

    CustomerDto fetchById(Long customerId);

    CustomerDto fetchCustomerByEmail(String email);

    CustomerDto updateCustomer(CustomerDto customer);

    void deleteByDocument(String document);

    void deleteByEmail(String email);

}
