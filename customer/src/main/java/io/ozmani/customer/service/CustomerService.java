package io.ozmani.customer.service;

import io.ozmani.clients.fraud.FraudCheckResponse;
import io.ozmani.clients.fraud.FraudClient;
import io.ozmani.customer.domain.CustomerRegistrationRequest;
import io.ozmani.customer.entity.Customer;
import io.ozmani.customer.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final FraudClient fraudClient;

    public void registerCustomer(CustomerRegistrationRequest request) {
        Customer customer = Customer.builder()
                .firstName(request.firstName())
                .lastName(request.lastName())
                .email(request.email())
                .build();

        customerRepository.saveAndFlush(customer);
        // TODO: check if fields are valid.
        // TODO: check fraudster
        FraudCheckResponse fraudCheckResponse =
                fraudClient.isFraudster(customer.getId());

        assert fraudCheckResponse != null;
        if (fraudCheckResponse.isFraudster()) {
            throw new IllegalStateException("This user is a fraudster!");
        }


        // TODO: send notification
    }
}
