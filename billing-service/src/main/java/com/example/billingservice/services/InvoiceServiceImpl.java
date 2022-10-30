package com.example.billingservice.services;

import com.example.billingservice.dto.InvoiceRequestDTO;
import com.example.billingservice.dto.InvoiceResponseDTO;
import com.example.billingservice.entities.Customer;
import com.example.billingservice.entities.Invoice;
import com.example.billingservice.exceptions.CustomerNotFoundException;
import com.example.billingservice.exceptions.InvoiceNotFoundException;
import com.example.billingservice.exceptions.InvoiceSaveException;
import com.example.billingservice.mappers.InvoiceMapper;
import com.example.billingservice.openfeign.CustomerRestClient;
import com.example.billingservice.repositories.InvoiceRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
@Slf4j
public class InvoiceServiceImpl implements InvoiceService {

    private InvoiceMapper invoiceMapper;
    private InvoiceRepository invoiceRepository;
    private CustomerRestClient customerRestClient;
    public InvoiceServiceImpl(InvoiceMapper invoiceMapper, InvoiceRepository invoiceRepository, CustomerRestClient customerRestClient) {
        this.invoiceMapper = invoiceMapper;
        this.invoiceRepository = invoiceRepository;
        this.customerRestClient = customerRestClient;
    }

    @Override
    public InvoiceResponseDTO save(InvoiceRequestDTO invoiceRequestDto) {
        if(invoiceRequestDto.getAmount() == null || invoiceRequestDto.getCustomerId()==null)
            throw new InvoiceSaveException("invoice need to have a idcustomer and amount");

        Invoice invoice=invoiceMapper.fromInvoiceRequestDTO(invoiceRequestDto);
        invoice.setId(UUID.randomUUID().toString());
        invoice.setDate(new Date());
        Customer customer;
        try {
            customer=customerRestClient.getCustomer(invoice.getCustomerId());
        }catch (Exception e){
            throw  new CustomerNotFoundException("Customer " + invoiceRequestDto.getCustomerId() + " not found");
        }

        Invoice invoiceSave=invoiceRepository.save(invoice);
        invoiceSave.setCustomer(customer);


        log.info("Invoice " + invoice.getId()+" saved successfully");
        return invoiceMapper.fromInvoice(invoiceSave);
    }

    @Override
    public InvoiceResponseDTO getInvoice(String invoiceId){
        Invoice invoice=findInvoiceById(invoiceId);
        Customer customer=customerRestClient.getCustomer(invoice.getCustomerId());
        invoice.setCustomer(customer);

        log.info("get invoice successfully");
        return invoiceMapper.fromInvoice(invoice);
    }

    @Override
    public List<InvoiceResponseDTO> invoicesByCustomerId(String customerId) {

        List<Invoice> invoices=invoiceRepository.findByCustomerId(customerId);

        log.info("get all invoices By Customer id successfully");

        return invoices.stream().map(invoice->{
            Customer customer=customerRestClient.getCustomer(invoice.getCustomerId());
            invoice.setCustomer(customer);
            return invoiceMapper.fromInvoice(invoice);
        }).collect(Collectors.toList());
    }

    @Override
    public List<InvoiceResponseDTO> allInvoices() {
        List<Invoice> invoices=invoiceRepository.findAll();
        log.info("get all invoice successfully");
        return invoices.stream().map(invoice->{
            Customer customer=customerRestClient.getCustomer(invoice.getCustomerId());
            invoice.setCustomer(customer);
            return invoiceMapper.fromInvoice(invoice);
        }).collect(Collectors.toList());
    }


    private Invoice findInvoiceById(String invoiceId){
        return invoiceRepository.findById(invoiceId).orElseThrow(() -> new InvoiceNotFoundException("Invoice " + invoiceId + " not found"));
    }
}
