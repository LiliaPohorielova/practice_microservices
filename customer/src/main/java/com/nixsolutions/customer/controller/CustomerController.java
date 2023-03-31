package com.nixsolutions.customer.controller;

import com.nixsolutions.customer.dto.CustomerRequest;
import com.nixsolutions.customer.service.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/v1/customers")
public record CustomerController(CustomerService customerService) {

    public void registerCustomer(@RequestBody CustomerRequest request) {
      log.info("new customer registration {}", request);
      customerService.registerCustomer(request);
    }
}
