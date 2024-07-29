package com.accenture.customers.services.implementation;

import com.accenture.customers.dto.CustomerDto;
import com.accenture.customers.entity.Customer;
import com.accenture.customers.exceptions.ResourceAlreadyExist;
import com.accenture.customers.exceptions.ResourceNotFound;
import com.accenture.customers.mappers.CustomerMapper;
import com.accenture.customers.repository.CustomerRepository;
import com.accenture.customers.services.ICustomerService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CustomerService implements ICustomerService {

    private CustomerRepository customerRepository;


    @Override
    public void createCustomer(CustomerDto customerDto) {
        Optional<Customer> existingCustomer = customerRepository.findByDocument(customerDto.getDocument());
        if (existingCustomer.isPresent()) {
            throw new ResourceAlreadyExist("client", customerDto.getDocument().toString());
        }
        Customer customer = CustomerMapper.mapDtoToCustomer(customerDto, new Customer());
      //    customer.setCreatedDate(LocalDateTime.now());
      //   customer.setCreatedBy("ADMIN");
        customerRepository.save(customer);
    }

    @Override
    public CustomerDto fetchCustomerByDocument(String document) {
        Customer customer = customerRepository.findByDocument(document).orElseThrow(
                () -> new ResourceNotFound("Client", "Document", document)
        );
        return CustomerMapper.mapCustomerToDTO(customer, new CustomerDto());
    }

    @Override
    public CustomerDto fetchCustomerByEmail(String email) {
        Customer customer = customerRepository.findByEmail(email).orElseThrow(
                () -> new ResourceNotFound("Client", "email", email)
        );
        return CustomerMapper.mapCustomerToDTO(customer, new CustomerDto());
    }

    @Override
    public List<CustomerDto> fetchAllCustomers() {
        List<Customer> customers = customerRepository.findAll();
        if (!customers.isEmpty()) {
            return customers.stream()
                    .map(customer -> CustomerMapper.mapCustomerToDTO(customer, new CustomerDto()))
                    .collect(Collectors.toList());
        } else {
            throw new ResourceNotFound("Resource", "Customer", "[]");
        }
    }



    @Override
    public void deleteByDocument(String document) {
        Optional<Customer> customerOptional = customerRepository.findByDocument(document);
        if (customerOptional.isPresent()) {
            customerRepository.deleteCustomerByDocument(document);
        } else {
            throw new ResourceNotFound("recurso", "documento", document );
        }
    }

    @Override
    public void deleteByEmail(String email) {

        Optional<Customer> customerOptional = customerRepository.findByEmail(email);

        if (customerOptional.isPresent()) {
            customerRepository.deleteCustomerByEmail(email);
        } else {
            throw new ResourceNotFound("recurso", "email", email );
        }

    }


    @Override
    public CustomerDto updateCustomer(CustomerDto customerDto) {
        Customer customer = customerRepository.findByDocument(customerDto.getDocument()).orElseThrow(
                () -> new ResourceNotFound("Client", "Document", customerDto.getDocument())
        );
            CustomerMapper.mapDtoToCustomer(customerDto, customer);
            customerRepository.save(customer);
            return customerDto;
    }
}
