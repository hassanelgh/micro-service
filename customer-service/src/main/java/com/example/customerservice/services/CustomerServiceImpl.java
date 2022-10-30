package com.example.customerservice.services;

import com.example.customerservice.dto.CustomerRequestDTO;
import com.example.customerservice.dto.CustomerResponseDTO;
import com.example.customerservice.entities.Customer;
import com.example.customerservice.exceptions.CustomerNotFoundException;
import com.example.customerservice.exceptions.CustomerSaveException;
import com.example.customerservice.mappers.CustomerMapper;
import com.example.customerservice.repositories.CustomerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;


@Service
@Transactional
@Slf4j
public class CustomerServiceImpl implements CustomerService {

    private CustomerRepository customerRepository;
    private CustomerMapper customerMapper;

    public CustomerServiceImpl(CustomerRepository customerRepository, CustomerMapper customerMapper) {
        this.customerRepository = customerRepository;
        this.customerMapper = customerMapper;
    }


    @Override
    public CustomerResponseDTO save(CustomerRequestDTO customerRequestDTO) {
        if(customerRequestDTO.getName() == null || customerRequestDTO.getEmail()==null)
            throw new CustomerSaveException("Customer need to have a name and email");
        Customer customer=customerMapper.fromCustomerRequestDTO(customerRequestDTO);
        customer.setId(UUID.randomUUID().toString());
        Customer customerSave=customerRepository.save(customer);

        log.info("customer saved  successfully ");

        return customerMapper.fromCustomer(customerSave);
    }

    @Override
    public CustomerResponseDTO getCustomer(String customerId){
        Customer customer = findCustomerById(customerId);

        log.info("get customer successfully ");
        return customerMapper.fromCustomer(customer);
    }

    @Override
    public CustomerResponseDTO update(CustomerRequestDTO customerRequestDTO){

        Customer customer = findCustomerById(customerRequestDTO.getId());


        if(customerRequestDTO.getName() != null) customer.setName(customerRequestDTO.getName());
        if(customerRequestDTO.getEmail() != null) customer.setEmail(customerRequestDTO.getEmail());
        Customer customerSave=customerRepository.save(customer);

        log.info("update customer successfully ");

        return customerMapper.fromCustomer(customerSave);
    }

    @Override
    public List<CustomerResponseDTO> allCustomers() {
        List<Customer> customers=customerRepository.findAll();
        log.info("get all customers successfully ");
        return customers.stream().map(customer -> customerMapper.fromCustomer(customer)).collect(Collectors.toList());
    }



    private Customer findCustomerById(String customerId){
        return customerRepository.findById(customerId).orElseThrow(() -> new CustomerNotFoundException("Customer not found"));
    }




}
