package io.ozmani.customer.domain;

public record CustomerRegistrationRequest(String firstName,
                                          String lastName,
                                          String email) {
}
