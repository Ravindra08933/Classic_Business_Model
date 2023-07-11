package com.example.cbm.controllers;
import com.example.cbm.entities.Customers;
import com.example.cbm.entities.Offices;
import com.example.cbm.entities.Payments;
import com.example.cbm.exceptions.CustomerNotFoundException;
import com.example.cbm.repositories.CustomerRepository;
import com.example.cbm.services.PaymentServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Payload;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
@RestController
@RequestMapping(value = "/api/v1")
public class PaymentController {

    private PaymentServiceInterface paymentServiceInterface;
    @Autowired
    public void setPaymentServiceInterface(PaymentServiceInterface paymentServiceInterface) {
        this.paymentServiceInterface = paymentServiceInterface;
    }

    private CustomerRepository customerRepository;
    @Autowired
    public void setCustomerRepository(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }
    @PostMapping(value="/payments/{id}")
    public ResponseEntity<String> postAddNewEmployee(@PathVariable Integer id,@RequestBody Payments payments){

        Customers customers = customerRepository.findByCustomerNumber(id);
        if(customers == null)
        {
            throw new CustomerNotFoundException("Customer Details not found");
        }
        Payments updated = paymentServiceInterface.savePayment(payments,customers);
        return new ResponseEntity<String>("Payment details added Successfully", HttpStatus.CREATED);
    }
    @PutMapping("/payments/update/{customer_number}/{check_number}")
    public ResponseEntity<String> updateCheckNumberForCustomer(
            @PathVariable Integer customer_number,
            @PathVariable String check_number,@RequestParam String new_number) {
        paymentServiceInterface.updateCheckNumberForCustomer(customer_number, check_number,new_number);
        return ResponseEntity.ok("Check number updated successfully");
    }
    @PutMapping(value = "update1/{customer_number}/{check_number}")
    public ResponseEntity<String> updateCheckAmountForCustomer(
            @PathVariable Integer customer_number,
            @PathVariable String check_number,@RequestParam BigDecimal amount)
    {
        paymentServiceInterface.updateCheckAmountForCustomer(customer_number, check_number,amount);
        return ResponseEntity.ok("Check number updated successfully");
    }
    @GetMapping("/payments/{payment_date}")
    public ResponseEntity<List<Payments>> getAllPaymentsByDate(
            @PathVariable("paymentDate") @DateTimeFormat(pattern = "dd-MM-yyyy") Date payment_date) {
        List<Payments> payments = paymentServiceInterface.getAllPaymentsByDate(payment_date);
        return new ResponseEntity<>(payments, HttpStatus.OK);
    }
    @GetMapping("/payments/{check_number}")
    public ResponseEntity<List<Payments>> getPaymentsByCheckNumber(@PathVariable String check_number) {
        List<Payments> payments = paymentServiceInterface.searchPaymentsByCheckNumber(check_number);
        return new ResponseEntity<>(payments, HttpStatus.OK);
    }
    @GetMapping("/payments/totalAmount/{customer_number}")
    public ResponseEntity<BigDecimal> getTotalAmountByCustomerNumber(@PathVariable Integer customer_number) {
        BigDecimal totalAmount = paymentServiceInterface.getTotalAmountByCustomerNumber(customer_number);
        return ResponseEntity.ok(totalAmount);
    }
    @GetMapping("payments/customers/{checkno}")
    public List<Customers> getCustomersByCheckNumber(@PathVariable String checkno) {
        return paymentServiceInterface.getCustomersByCheckNumber(checkno);
    }
    @GetMapping("/payments/customers/maxamount")
    public ResponseEntity<Customers> getCustomerWithMaxPaymentAmount() {
        Customers customer = paymentServiceInterface.findCustomerWithMaxPaymentAmount();
        if (customer == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(customer);
    }
    @GetMapping("payments/{payment_date}/customers")
    public List<Customers> getCustomersByPaymentDate(@PathVariable("payment_date") @DateTimeFormat(pattern = "yyyy-MM-dd") Date payment_date) {
        return paymentServiceInterface.getCustomersByPaymentDate(payment_date);
    }
    @GetMapping("/payment/highest_collection_office_details")
    public ResponseEntity<Offices> getOfficeWithMaxPaymentCollection() {
        Offices office = paymentServiceInterface.getOfficeWithMaxPaymentCollection();
        return new ResponseEntity<Offices>(office, HttpStatus.OK);
    }
    @GetMapping("payments/a/{customer_number}")
    public List<Payments> getPaymentsByCustomer(@PathVariable Integer customer_number) {
        return paymentServiceInterface.getPaymentsByCustomer(customer_number);
    }
    @GetMapping("/customers/{start_Pay_date}/{end_Pay_date}")
    public List<Payments> getPaymentsByDateRange(
            @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date start_Pay_date,
            @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date end_Pay_date
    ) {
        return paymentServiceInterface.getPaymentsByDateRange(start_Pay_date, end_Pay_date);
    }
}

