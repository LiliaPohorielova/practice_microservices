package com.nixsolutions.customer.service;

import com.nixsolutions.clients.fraud.FraudCheckResponse;
import com.nixsolutions.clients.fraud.FraudClient;
import com.nixsolutions.clients.notification.NotificationClient;
import com.nixsolutions.clients.notification.NotificationRequest;
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
    private final NotificationClient notificationClient;

    public void registerCustomer(CustomerRequest request) {
        Customer customer = Customer.builder()
            .firstName(request.firstName())
            .lastName(request.lastName())
            .email(request.email())
            .build();

        //TODO: check if email valid
        //TODO: check if email not taken

        customerRepository.saveAndFlush(customer);

        //TODO: check if fraudster (call to another Microservice)
        FraudCheckResponse response = fraudClient.isFraudster(customer.getId());

        if(response.isFraudster()) {
            throw new IllegalStateException("fraudster");
        }


        //TODO: send notification (can be replaced by Message Queue)
        String message = String.format("Hi %s, welcome to our company!",  customer.getFirstName());
        notificationClient.sendNotification(
            NotificationRequest.builder()
                .toCustomerId(customer.getId())
                .toCustomerEmail(customer.getEmail())
                .message(message)
                .build()
        );
    }
}
