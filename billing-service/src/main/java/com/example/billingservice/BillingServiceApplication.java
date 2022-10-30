package com.example.billingservice;

import com.example.billingservice.dto.InvoiceRequestDTO;
import com.example.billingservice.entities.Customer;
import com.example.billingservice.openfeign.CustomerRestClient;
import com.example.billingservice.services.InvoiceService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;
import java.util.List;

@SpringBootApplication
@EnableFeignClients
public class BillingServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(BillingServiceApplication.class, args);
	}



	@Bean
	CommandLineRunner commandLineRunner(InvoiceService invoiceService, CustomerRestClient customerRestClient){
		return args -> {

			List<Customer> customers = customerRestClient.allCustomers();
			for (Customer customer : customers){
				invoiceService.save(new InvoiceRequestDTO(BigDecimal.valueOf(125*Math.random()+2122.0002),customer.getId()));
			}

		};

	}
}
