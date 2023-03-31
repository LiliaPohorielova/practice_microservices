package com.nixsolutions.customer.service;

import com.nixsolutions.customer.dto.CustomerRequest;
import com.nixsolutions.customer.entity.Customer;
import com.nixsolutions.customer.repository.CustomerRepository;
import org.springframework.stereotype.Service;

@Service
public record CustomerService(CustomerRepository customerRepository) {

    public void registerCustomer(CustomerRequest request) {
        Customer customer = Customer.builder()
            .firstName(request.firstName())
            .lastName(request.lastName())
            .email(request.email())
            .build();

        //TODO: check if email valid
        //TODO: check if email not taken

        customerRepository.save(customer);
    }
}
