package com.example.customerservice.services;

import com.example.customerservice.dto.CustomerRequestDTO;
import com.example.customerservice.dto.CustomerResponseDTO;
import com.example.customerservice.exceptions.CustomerNotFoundException;

import java.util.List;

public interface CustomerService {
    CustomerResponseDTO save(CustomerRequestDTO customerRequestDTO);
    CustomerResponseDTO getCustomer(String customerId) ;
    CustomerResponseDTO update(CustomerRequestDTO customerRequestDTO) ;
    List<CustomerResponseDTO> allCustomers();

}
