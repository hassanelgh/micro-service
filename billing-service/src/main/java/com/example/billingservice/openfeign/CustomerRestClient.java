package com.example.billingservice.openfeign;


import com.example.billingservice.entities.Customer;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient("CUSTOMER-SERVICE")
public interface CustomerRestClient {

    @GetMapping(path = "/api/customers/{customerId}")
    Customer getCustomer(@PathVariable(name = "customerId") String customerId);

    @GetMapping(path = "/api/customers")
    List<Customer> allCustomers();



}
