package com.example.billingservice.mappers;

import com.example.billingservice.dto.InvoiceRequestDTO;
import com.example.billingservice.dto.InvoiceResponseDTO;
import com.example.billingservice.entities.Invoice;
import lombok.Data;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface InvoiceMapper {
    //@Mapping(source = "customerId",target = "customerId" ,defaultExpression = "java(java.util.UUID.randomUUID().toString())")
    //@Mapping(target = "date" ,defaultExpression = "java(new java.util.Date())")
    Invoice fromInvoiceRequestDTO(InvoiceRequestDTO invoiceRequestDto);
    InvoiceResponseDTO fromInvoice(Invoice invoice);
}
