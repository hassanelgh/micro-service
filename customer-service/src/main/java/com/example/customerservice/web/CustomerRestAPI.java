package com.example.customerservice.web;


import com.example.customerservice.dto.CustomerRequestDTO;
import com.example.customerservice.dto.CustomerResponseDTO;

import com.example.customerservice.exceptions.CustomerNotFoundException;
import com.example.customerservice.exceptions.CustomerSaveException;
import com.example.customerservice.services.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CustomerRestAPI {

    private CustomerService customerService;


    public CustomerRestAPI(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/customers")
    public List<CustomerResponseDTO> allCustomers() {
        return customerService.allCustomers();
    }

    @GetMapping("/customers/{customerId}")
    public CustomerResponseDTO getCustomer(@PathVariable(name = "customerId") String customerId) {
        return customerService.getCustomer(customerId);
    }


    @PostMapping("/customers")
    public CustomerResponseDTO save(@RequestBody CustomerRequestDTO customerRequestDTO) {
        return customerService.save(customerRequestDTO);
    }


    @PutMapping("/customers")
    public CustomerResponseDTO update(@RequestBody CustomerRequestDTO customerRequestDTO) {
        return customerService.update(customerRequestDTO);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> exceptionsHandler(Exception e){
        if(e instanceof CustomerNotFoundException)
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
        else if(e instanceof CustomerSaveException)
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
        return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }


















}
