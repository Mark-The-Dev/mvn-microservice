package io.ozmani.customer.service;

import io.ozmani.customer.domain.CustomerRegistrationRequest;
import io.ozmani.customer.domain.FraudCheckResponse;
import io.ozmani.customer.entity.Customer;
import io.ozmani.customer.repository.CustomerRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public record CustomerService(CustomerRepository customerRepository, RestTemplate restTemplate) {

    public void registerCustomer(CustomerRegistrationRequest request) {
        Customer customer = Customer.builder()
                .firstName(request.firstName())
                .lastName(request.lastName())
                .email(request.email())
                .build();

        customerRepository.saveAndFlush(customer);
        // TODO: check if fields are valid.
        // TODO: check fraudster
        FraudCheckResponse fraudCheckResponse = restTemplate.getForObject(
                "http:localhost:8081/api/v1/fraud-check/{customerId}",
                FraudCheckResponse.class,
                customer.getId()
        );

        assert fraudCheckResponse != null;
        if (fraudCheckResponse.isFraudster()) {
            throw new IllegalStateException("This user is a fraudster!");
        }


        // TODO: send notification
    }
}
