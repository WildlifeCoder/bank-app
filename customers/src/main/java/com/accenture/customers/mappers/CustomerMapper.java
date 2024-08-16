package com.accenture.customers.mappers;

import com.accenture.customers.dto.CustomerDto;
import com.accenture.customers.dto.CustomerWithAccounts;
import com.accenture.customers.entity.Customer;

public class CustomerMapper {

    public static CustomerDto mapCustomerToDTO(Customer customer, CustomerDto customerDto) {
        customerDto.setAddress(customer.getAddress());
        customerDto.setDocument(customer.getDocument());
        customerDto.setName(customer.getName());
        customerDto.setPhone(customer.getPhone());
        customerDto.setEmail(customer.getEmail());
        return customerDto;
    }

    public static CustomerWithAccounts mapCustomerToDTOWithAccounts(Customer customer, CustomerWithAccounts customerWithAccounts) {
        customerWithAccounts.setAddress(customer.getAddress());
        customerWithAccounts.setDocument(customer.getDocument());
        customerWithAccounts.setName(customer.getName());
        customerWithAccounts.setPhone(customer.getPhone());
        customerWithAccounts.setEmail(customer.getEmail());
        return customerWithAccounts;
    }

    public static Customer mapDtoToCustomer(CustomerDto customerDto, Customer customer) {
        customer.setAddress(customerDto.getAddress());
        customer.setDocument(customerDto.getDocument());
        customer.setName(customerDto.getName());
        customer.setPhone(customerDto.getPhone());
        customer.setEmail(customerDto.getEmail());
        return customer;
    }
}
