package com.nixsolutions.customer.dto;

public record CustomerRequest(
    String firstName,
    String lastName,
    String email) {
}
