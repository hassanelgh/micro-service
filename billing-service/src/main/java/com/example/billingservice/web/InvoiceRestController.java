package com.example.billingservice.web;


import com.example.billingservice.dto.InvoiceRequestDTO;
import com.example.billingservice.dto.InvoiceResponseDTO;
import com.example.billingservice.entities.Invoice;
import com.example.billingservice.exceptions.CustomerNotFoundException;
import com.example.billingservice.exceptions.InvoiceNotFoundException;
import com.example.billingservice.exceptions.InvoiceSaveException;
import com.example.billingservice.services.InvoiceService;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class InvoiceRestController {


    private InvoiceService invoiceService;
    public InvoiceRestController(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }

    @GetMapping("/invoices")
    public List<InvoiceResponseDTO>  Invoices() {
        return invoiceService.allInvoices();
    }

    @GetMapping("/invoices/{invoiceId}")
    public InvoiceResponseDTO getInvoice(@PathVariable(name = "invoiceId") String invoiceId) {
        return invoiceService.getInvoice(invoiceId);
    }


    @GetMapping("/invoicesByCustomer/{customerId}")
    public List<InvoiceResponseDTO> getInvoicesByCustomerId(@PathVariable(name = "customerId") String customerId) {
        return invoiceService.invoicesByCustomerId(customerId);
    }



    @PostMapping("/invoices")
    public InvoiceResponseDTO save(@RequestBody InvoiceRequestDTO invoiceRequestDTO){
        return invoiceService.save(invoiceRequestDTO);
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> exceptionsHandler(Exception e){
        if(e instanceof CustomerNotFoundException || e instanceof InvoiceNotFoundException){
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
        else if(e instanceof InvoiceSaveException){
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
