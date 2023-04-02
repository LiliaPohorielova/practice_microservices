package com.nixsolutions.customer.service;

import com.nixsolutions.clients.fraud.FraudCheckResponse;
import com.nixsolutions.clients.fraud.FraudClient;
import com.nixsolutions.customer.dto.CustomerRequest;
import com.nixsolutions.customer.entity.Customer;
import com.nixsolutions.customer.repository.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final FraudClient fraudClient;

    public void registerCustomer(CustomerRequest request) {
        Customer customer = Customer.builder()
            .firstName(request.firstName())
            .lastName(request.lastName())
            .email(request.email())
            .build();

        //TODO: check if email valid
        //TODO: check if email not taken

        customerRepository.saveAndFlush(customer);

        //TODO: check if fraudster (this old code was replaced!)
//        FraudCheckResponse response = restTemplate.getForObject(
//            "http://FRAUD/api/v1/fraud-check/{customerId}",
//            FraudCheckResponse.class,
//            customer.getId()
//        );
        FraudCheckResponse response = fraudClient.isFraudster(customer.getId());

        if(response.isFraudster()) {
            throw new IllegalStateException("fraudster");
        }


        //TODO: send notification

    }
}
