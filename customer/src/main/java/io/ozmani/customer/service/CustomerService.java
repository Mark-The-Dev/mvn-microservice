package io.ozmani.customer.service;

import io.ozmani.clients.fraud.FraudCheckResponse;
import io.ozmani.clients.fraud.FraudClient;
import io.ozmani.clients.notification.NotificationClient;
import io.ozmani.clients.notification.NotificationRequest;
import io.ozmani.clients.notification.NotificationResponse;
import io.ozmani.customer.domain.CustomerRegistrationRequest;
import io.ozmani.customer.entity.Customer;
import io.ozmani.customer.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final FraudClient fraudClient;
    private final NotificationClient notificationClient;

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
        NotificationRequest notificationRequest = new NotificationRequest(customer.getId(), "Welcome to " +
                "Ozmani Services, we are happy to have you!", customer.getEmail());
        NotificationResponse notificationResponse = notificationClient.publishNotification(notificationRequest);
        log.info("notification response received for: {}",customer.getId());
    }
}
