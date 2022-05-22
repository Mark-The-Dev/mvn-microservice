package io.ozmani.customer.service;

import io.ozmani.customer.domain.CustomerRegistrationRequest;
import io.ozmani.customer.entity.Customer;
import io.ozmani.customer.repository.CustomerRepository;
import org.springframework.stereotype.Service;

@Service
public record CustomerService(CustomerRepository customerRepository) {

    public void registerCustomer(CustomerRegistrationRequest request) {
        Customer customer = Customer.builder()
                .firstName(request.firstName())
                .lastName(request.lastName())
                .email(request.email())
                .build();

        // TODO: check if fields are valid.
        customerRepository.save(customer);

    }
}
