package com.example.cbm.controllers;
import com.example.cbm.dtos.CustomerOrderPaymentDetails;
import com.example.cbm.entities.Customers;
import com.example.cbm.entities.Payments;
import com.example.cbm.services.CustomerServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;
@RestController
@RequestMapping(value = "/api/v1/customers")
public class CustomerController {
    private CustomerServiceInterface customerServiceInterface;
    @Autowired
    public void setCustomerServiceInterface(CustomerServiceInterface customerServiceInterface) {
        this.customerServiceInterface = customerServiceInterface;
    }

    @PostMapping(value = "/")
    public ResponseEntity<String> saveCustomer(@RequestBody Customers customers)
    {
        customerServiceInterface.saveCustomer(customers);
        return new ResponseEntity<>("Record Created Successfully",HttpStatus.CREATED);
    }
    @GetMapping(value="/customer_name/{customer_name}")
    public ResponseEntity<List<Customers>> getCustomerByName(@PathVariable String customer_name)
    {
        List<Customers> customers= customerServiceInterface.searchCustomersByName(customer_name);
        return new ResponseEntity<>(customers,HttpStatus.OK);
    }
    @GetMapping(value = "/city/{city}")
    public ResponseEntity<List<Customers>> getCustomerByCity(@PathVariable String city)
    {
        List<Customers> customers= customerServiceInterface.searchCustomersByCity(city);
        return new ResponseEntity<>(customers,HttpStatus.OK);
    }
    @GetMapping(value="/customer_number/{customer_number}")
    public ResponseEntity<Customers> getCustomerByNumber(@PathVariable Integer customer_number)
    {
        Customers customers= customerServiceInterface.getCustomerByCustomerNumber(customer_number);
        return new ResponseEntity<>(customers,HttpStatus.OK);
    }
    @GetMapping("/sales_rep_employee_number/{sales_rep_employee_number}")
    public List<Customers> searchCustomersBySalesRepEmployeeNumber(@PathVariable Integer sales_rep_employee_number) {
        return customerServiceInterface.searchCustomersBySalesRepEmployeeNumber(sales_rep_employee_number);
    }
    @GetMapping(value = "/country/{country}")
    public ResponseEntity<List<Customers>> getCustomerByCountry(@PathVariable String country)
    {
        List<Customers> customers= customerServiceInterface.searchByCountry(country);
        return new ResponseEntity<>(customers,HttpStatus.OK);
    }
    @GetMapping(value = "/phone/{phone}")
    public ResponseEntity<Customers> getCustomerByPhone(@PathVariable String phone)
    {
        Customers customers= customerServiceInterface.searchByPhoneNumber(phone);
        return new ResponseEntity<>(customers,HttpStatus.OK);
    }
    @GetMapping(value = "/contact_firstname/{contact_firstname}")
    public ResponseEntity<List<Customers>> getCustomerByFirstName(@PathVariable String contact_firstname)
    {
        List<Customers> customers= customerServiceInterface.searchCustomersByFirstName(contact_firstname);
        return new ResponseEntity<>(customers,HttpStatus.OK);
    }
    @GetMapping(value = "/contact_lastname/{contact_lastname}")
    public ResponseEntity<List<Customers>> getCustomerByLastName(@PathVariable String contact_lastname)
    {
        List<Customers> customers= customerServiceInterface.searchByContactLastName(contact_lastname);
        return new ResponseEntity<>(customers,HttpStatus.OK);
    }
    @PutMapping(value="/{customer_number}/{customer_name}")
    public ResponseEntity<Customers> updateName(@PathVariable Integer customer_number,@PathVariable String customer_name)
    {
        Customers customers = customerServiceInterface.updateCustomerName(customer_number,customer_name);
        return new ResponseEntity<>(customers,HttpStatus.OK);
    }
    @PutMapping(value="/{customer_number}/{customer_lastname}")
    public ResponseEntity<Customers> updateLastName(@PathVariable Integer customer_number,@PathVariable String customer_lastname)
    {
        Customers customers = customerServiceInterface.updateContactFirstName(customer_number,customer_lastname);
        return new ResponseEntity<>(customers,HttpStatus.OK);
    }
    @PutMapping(value="/{customer_number}/{customer_firstname}")
    public ResponseEntity<Customers> updateFirstName(@PathVariable Integer customer_number,@PathVariable String customer_firstname)
    {
        Customers customers = customerServiceInterface.updateContactLastName(customer_number,customer_firstname);
        return new ResponseEntity<>(customers,HttpStatus.OK);
    }
    @PutMapping(value="/{customer_number}")
    public ResponseEntity<String> updateAddress(@PathVariable Integer customer_number,@RequestParam String addressLine1,@RequestParam String addressLine2,@RequestParam String city,@RequestParam String state,@RequestParam String country,@RequestParam String postalcode)
    {
        String s = customerServiceInterface.updateCustomerAddress(customer_number,addressLine1,addressLine2,city,state,country,postalcode);
        return new ResponseEntity<>(s,HttpStatus.OK);
    }
    @GetMapping(value="/credit_limit/{credit_limt}")
    public ResponseEntity<List<Customers>> searchCustomerByCreditLimit(@PathVariable BigDecimal credit_limt)
    {
        List<Customers> customers = customerServiceInterface.searchCustomersByCreditLimit(credit_limt);
        return new ResponseEntity<>(customers,HttpStatus.OK);
    }
    @GetMapping(value="/postal_code/{postal_code}")
    public ResponseEntity<List<Customers>> searchCustomerByPostalCode(@PathVariable String postal_code)
    {
        List<Customers> customers = customerServiceInterface.searchCustomersByPostalCode(postal_code);
        return new ResponseEntity<>(customers,HttpStatus.OK);
    }
    @GetMapping(value="/credit_range/{start}/{end}")
    public ResponseEntity<List<Customers>> searchCustomerByCreditLimitRange(@PathVariable BigDecimal start,@PathVariable BigDecimal end)
    {
        List<Customers> customers = customerServiceInterface.getCustomersByCreditRange(start,end);
        return new ResponseEntity<>(customers,HttpStatus.OK);
    }
    @GetMapping("/{pageSize}/{pageNo}/{sortBy}/{sortDir}")
    public ResponseEntity<Collection<Customers>> getCustomersByPage(@PathVariable int pageSize, @PathVariable int pageNo, @PathVariable String sortBy, @PathVariable String sortDir)
    {
        Sort.Direction sortDirection = Sort.Direction.ASC;
        if (sortDir.equalsIgnoreCase("desc"))
            sortDirection = Sort.Direction.DESC;
        Collection<Customers> customers =  customerServiceInterface.getCustomersByPage(pageNo, sortBy, sortDirection, pageSize);
        return new ResponseEntity<>(customers,HttpStatus.OK);
    }
    @GetMapping("/getbyfirstnamelike/{fn}")
    public ResponseEntity<List<Customers>> searchCustomersByFirstName(@PathVariable String fn) {
        return new ResponseEntity<>(customerServiceInterface.searchCustomersByFirstName(fn),HttpStatus.OK) ;
    }
    @GetMapping("/gt/{credit_limit}")
    public ResponseEntity<List<Customers>> searchCustomersByGreaterCreditLimit(@PathVariable BigDecimal credit_limit) {
        return new ResponseEntity<>(customerServiceInterface.searchCustomersByGreaterCreditLimit(credit_limit),HttpStatus.OK) ;
    }
    @GetMapping("/lt/{credit_limit}")
    public ResponseEntity<List<Customers>> searchCustomersByLowerCreditLimit(@PathVariable BigDecimal credit_limit) {
        return new ResponseEntity<>(customerServiceInterface.searchCustomersByLowerCreditLimit(credit_limit),HttpStatus.OK);
    }
    @GetMapping("/{customer_id}/order_product_staffdetails")
    public ResponseEntity<Collection<Object[]>> getOrderProductStaffDetails(@PathVariable Integer customer_id) {
        return new ResponseEntity<>(customerServiceInterface.getOrderDetailsForCustomer(customer_id),HttpStatus.OK);
    }
    @GetMapping("/{customer_id}/paymentdetails")
    public ResponseEntity<List<Payments>> getPaymentDetailsByCustomerId(@PathVariable Integer customer_id) {
        return new ResponseEntity<>(customerServiceInterface.getPaymentDetailsForCustomer(customer_id),HttpStatus.OK);
    }
    @GetMapping(value = "/{customer_name}/order_payment_details")
    public ResponseEntity<Collection<CustomerOrderPaymentDetails>> getCustomerOrderPaymentDetails(@PathVariable String customer_name)
    {
        return new ResponseEntity<>(customerServiceInterface.getOrderAndPaymentDetailsForCustomer(customer_name),HttpStatus.OK);
    }
}

