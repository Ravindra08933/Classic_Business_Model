package com.example.cbm.services;
import com.example.cbm.dtos.CustomerOrderPaymentDetails;
import com.example.cbm.entities.Customers;
import com.example.cbm.entities.Payments;
import com.example.cbm.exceptions.CustomerNotFoundException;
import com.example.cbm.repositories.CustomerRepository;
import com.example.cbm.repositories.OrderRepository;
import com.example.cbm.repositories.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;
@Service
public class CustomerServiceImpl implements CustomerServiceInterface {
    private CustomerRepository customerRepository;
    private OrderRepository ordersRepository;
    private PaymentRepository paymentsRepository;
    @Autowired
    public void setCustomerRepository(CustomerRepository customerRepository, OrderRepository ordersRepository, PaymentRepository paymentsRepository) {
        this.customerRepository = customerRepository;
        this.ordersRepository = ordersRepository;
        this.paymentsRepository = paymentsRepository;
    }

    public String saveCustomer(Customers customers) {
        customerRepository.save(customers);
        return "Record Created Successfully";
    }

    public List<Customers> searchCustomersByName(String name) {
        List<Customers> customers = customerRepository.findByCustomerName(name);
        if (customers.isEmpty())
            throw new CustomerNotFoundException("Customer Details not found");
        return customers;
    }

    public List<Customers> searchCustomersByCity(String city) {
        List<Customers> customers = customerRepository.findByCity(city);
        if (customers.isEmpty())
            throw new CustomerNotFoundException("Customer Details not found");
        return customers;
    }

    public Customers getCustomerByCustomerNumber(Integer customerNumber) {
        return  customerRepository.findById(customerNumber).orElseThrow(()-> new CustomerNotFoundException("Customer Details not found " + customerNumber));
    }

    public List<Customers> searchCustomersBySalesRepEmployeeNumber(Integer employeeNumber) {
        List<Customers> customers = customerRepository.findBySalesRepEmployeeNumber(employeeNumber);
        return customers;
    }

    public List<Customers> searchByCountry(String country) {
        List<Customers> customers = customerRepository.findByCountry(country);
        if (customers.isEmpty())
            throw new CustomerNotFoundException("CCustomer Details not found");
        return customers;
    }

    public Customers searchByPhoneNumber(String phone) {
        Customers customer = customerRepository.findByPhone(phone);
        if (customer == null)
            throw new CustomerNotFoundException("Customer Details not found");
        return customer;
    }

    public List<Customers> searchByContactLastName(String lastName) {

        List<Customers> customers = customerRepository.findByContactLastName(lastName);
        if (customers.isEmpty())
            throw new CustomerNotFoundException("Customer Details not found");
        return customers;
    }

    public Customers updateCustomerName(Integer customerNumber, String newName) {
        Customers customer = customerRepository.findById(customerNumber).orElseThrow(()-> new CustomerNotFoundException("Customer Details not found"));
        customer.setCustomerName(newName);
        return customer;
    }

    public Customers updateContactLastName(Integer customerNumber, String newName) {
        Customers customer = customerRepository.findById(customerNumber).orElseThrow(()-> new CustomerNotFoundException("Customer Details not found"));
        customer.setContactLastName(newName);
        return customer;
    }

    public Customers updateContactFirstName(Integer customerNumber, String newFirstName) {
        Customers customer = customerRepository.findById(customerNumber).orElseThrow(()-> new CustomerNotFoundException("Customer Details not found"));
        customer.setContactFirstName(newFirstName);
        return customer;
    }

    public String updateCustomerAddress(Integer customerNumber, String newAddressLine1, String newAddressLine2, String newCity, String newState, String newCountry, String newPostalCode) {
        Customers customer = customerRepository.findById(customerNumber).orElseThrow(()-> new CustomerNotFoundException("Customer Details not found"));
        customer.setAddressLine1(newAddressLine1);
        customer.setAddressLine2(newAddressLine2);
        customer.setCity(newCity);
        customer.setState(newState);
        customer.setCountry(newCountry);
        customer.setPostalCode(newPostalCode);
        return "customer address updated successfully";
    }


    public List<Customers> searchCustomersByPostalCode(String postalCode) {
        List<Customers> customers = customerRepository.findByPostalCode(postalCode);
        if (customers.isEmpty())
            throw new CustomerNotFoundException("Customer Details not found");
        return customers;
    }

    public List<Customers> getCustomersByCreditRange(BigDecimal minCredit, BigDecimal maxCredit) {
        List<Customers> customers = customerRepository.findAll();
        List<Customers> customers1 = customers.stream()
                .filter(c -> c.getCreditLimit().compareTo(minCredit) >= 0 && c.getCreditLimit().compareTo(maxCredit) <= 0)
                .collect(Collectors.toList());
        if (customers1.isEmpty())
            throw new CustomerNotFoundException("Customer Details not found");
        return customers1;
    }

    public Collection<Customers> getCustomersByPage(int pageNo, String sortBy, Sort.Direction sortDirection, int pageSize) {
        Sort sort = Sort.by(sortDirection, sortBy);
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
        Page<Customers> customerPage = customerRepository.findAll(pageable);
        List<Customers> customers = customerPage.getContent();
        return customers;
    }
    public List<Customers> searchCustomersByFirstName(String searchString) {
        List<Customers> allCustomers = customerRepository.findAll();
        List<Customers> customers1 = allCustomers.stream()
                .filter(customer -> customer.getContactFirstName().contains(searchString))
                .collect(Collectors.toList());
        if (customers1.isEmpty())
            throw new CustomerNotFoundException("Customer Details not found");
        return customers1;
    }

    public List<Customers> searchCustomersByGreaterCreditLimit(BigDecimal creditLimit) {
        List<Customers> allCustomers = customerRepository.findAll();
        List<Customers> customers1 = allCustomers.stream()
                .filter(customer -> customer.getCreditLimit().compareTo(creditLimit) > 0)
                .collect(Collectors.toList());
        if (customers1.isEmpty())
            throw new CustomerNotFoundException("Customer Details not found");
        return customers1;
    }

    public List<Customers> searchCustomersByLowerCreditLimit(BigDecimal creditLimit) {
        List<Customers> customersList = customerRepository.findAll();
        List<Customers> customers1 = customersList.stream()
                .filter(customer -> customer.getCreditLimit().compareTo(creditLimit) < 0)
                .collect(Collectors.toList());
        if (customers1.isEmpty())
            throw new CustomerNotFoundException("Customer Details not found");
        return customers1;
    }

    public List<Payments> getPaymentDetailsForCustomer(Integer customerNumber) {
        List<Payments> payments = customerRepository.getPaymentDetailsForCustomer(customerNumber);
        if (payments.isEmpty())
            throw new CustomerNotFoundException("Customer Details not found");
        return payments;
    }

    public List<Customers> searchCustomersByCreditLimit(BigDecimal creditLimt) {
        return customerRepository.findByCreditLimit(creditLimt);
    }

    public Collection<Object[]> getOrderDetailsForCustomer(Integer customerNumber) {
        Customers customer = customerRepository.findById(customerNumber).orElseThrow(()->new CustomerNotFoundException("Customer Details not found"));
        Collection<Object[]> objects = customerRepository.getOrderDetailsForCustomer(customerNumber);
        if (objects.isEmpty())
            throw new CustomerNotFoundException("Customer Details not found");
        return objects;
    }

    public Collection<CustomerOrderPaymentDetails> getOrderAndPaymentDetailsForCustomer(String customerName) {
        return customerRepository.getOrderAndPaymentDetailsForCustomer(customerName);
    }
}
