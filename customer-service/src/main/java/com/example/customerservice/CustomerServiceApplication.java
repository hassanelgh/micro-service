package com.example.customerservice;

import com.example.customerservice.dto.CustomerRequestDTO;
import com.example.customerservice.services.CustomerService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication

public class CustomerServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CustomerServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(CustomerService customerService){
        return args -> {
            customerService.save(new CustomerRequestDTO(null,"customer","customer@gmail.com"));
            customerService.save(new CustomerRequestDTO(null,"customer1","customer2@gmail.com"));
            customerService.save(new CustomerRequestDTO(null,"customer2","customer3@gmail.com"));
        };
    }


}
