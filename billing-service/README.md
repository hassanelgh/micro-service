# Billing Service  :

### Dépendances et plugins :

        <dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-data-jpa</artifactId>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-web</artifactId>
		</dependency>
		<dependency>
			<groupId>org.springframework.cloud</groupId>
			<artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
		</dependency>

		<dependency>
			<groupId>com.h2database</groupId>
			<artifactId>h2</artifactId>
			<scope>runtime</scope>
		</dependency>
        ...

-   [pom.xml](./pom.xml)


### Entities :
- customer [>>](./src/main/java/com/example/billingservice/entities/Customer.java) :

    <img src="images/img.png" alt="">

- Invoice  [>>](./src/main/java/com/example/billingservice/entities/Invoice.java):
  
    <img src="images/img_1.png" alt="">


###  repositories :

- InvoiceRepository [>>](./src/main/java/com/example/billingservice/repositories/InvoiceRepository.java):

    <img src="images/img_2.png" alt="">

### DTO :

- Création de deux entités dto le premier est `InvoiceRequestDTO` pour recevoir la requête de l’utilisateur, le deuxième est `InvoiceResponseDTO` est utilisé pour répondre aux utilisateurs : [>>](./src/main/java/com/example/billingservice/dto)
    <img src="images/img_3.png" alt="">


### Mappers :
- Pour faire le mapping entre les entités et les DTO on a utilisé `mapstruct`
    - InvoiceMapper [>>](./src/main/java/com/example/billingservice/mappers/InvoiceMapper.java):

   <img src="images/img_4.png" alt="">


### Exception :
- CustomerNotFoundException : [>>](./src/main/java/com/example/billingservice/exceptions/CustomerNotFoundException.java)
- InvoiceNotFoundException : [>>](./src/main/java/com/example/billingservice/exceptions/InvoiceNotFoundException.java)
- InvoiceSaveException (dans le cas l'utilisateur n'a pas entré l'un des attributes) : [>>](./src/main/java/com/example/billingservice/exceptions/InvoiceSaveException.java)

### OpenFeign :
- est utilisé pour la communication entre les micro-services 
- CustomerRestClient [>>](./src/main/java/com/example/billingservice/openfeign/CustomerRestClient.java):

   <img src="images/img_5.png" alt="">


### Services :
- InvoiceService :
  <img src="images/img_6.png" alt="">

- InvoiceServiceImpl  [>>](./src/main/java/com/example/billingservice/services/InvoiceServiceImpl.java)

### Web :
- InvoiceRestController [>>](./src/main/java/com/example/billingservice/web/InvoiceRestController.java)
    - `InvoiceRestController(InvoiceService invoiceService)`
    - `List<InvoiceResponseDTO>  Invoices()`
    - `InvoiceResponseDTO getInvoice(@PathVariable(name = "invoiceId") String invoiceId)`
    - `List<InvoiceResponseDTO> getInvoicesByCustomerId(@PathVariable(name = "customerId") String customerId)`
    - `InvoiceResponseDTO save(@RequestBody InvoiceRequestDTO invoiceRequestDTO)`
    - `ResponseEntity<String> exceptionsHandler(Exception e)`

### application.properties :

  <img src="images/img_7.png" alt="">

- [application.properties](./src/main/resources/application.properties)


### BillingServiceApplication :

  <img src="images/img_8.png" alt="">

- [BillingServiceApplication](./src/main/java/com/example/billingservice/BillingServiceApplication.java)


### Test :

- getAllInvoices :

  <img src="images/img_9.png" alt="">

- getInvoice :

  <img src="images/img_10.png" alt="">

    - Cas Invoice not found :

      <img src="images/img_11.png" alt="">

- postInvoice :

  <img src="images/img_12.png" alt="">

  - Cas enregistrer invoice sans customerId ou amount:

    <img src="images/img_13.png" alt="">
    
  - Cas customer id n'existe pas :

    <img src="images/img_14.png" alt="">
  
- getInvoice by customerId :

  <img src="images/img_15.png" alt="">

    - Cas Customer not found :

      <img src="images/img_16.png" alt="">


