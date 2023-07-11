package com.example.cbm.services;
import com.example.cbm.entities.Customers;
import com.example.cbm.entities.Offices;
import com.example.cbm.entities.Payments;
import com.example.cbm.exceptions.CustomerNotFoundException;
import com.example.cbm.repositories.CustomerRepository;
import com.example.cbm.repositories.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
@Service
public class PaymentServiceImpl implements PaymentServiceInterface {
    PaymentRepository paymentRepository;
    CustomerRepository customerRepository;
    @Autowired
    public void setCustomerRepository(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Autowired
    public void setPaymentRepository(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }
    private EntityManager entityManager;
    @Autowired
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
    public Payments savePayment(Payments payments, Customers customers)
    {
        payments.getCustomers().setCustomerNumber(customers.getCustomerNumber());
        return paymentRepository.save(payments);
    }
    public void updateCheckNumberForCustomer(Integer customerNumber,String old, String newCheckNumber) {
        List<Payments> payment = paymentRepository.findByCustomers_CustomerNumber(customerNumber);
        if(payment.isEmpty())
            throw new CustomerNotFoundException("Customer Details not found");
        for (Payments p:payment) {
            if (p.getCheckNumber().equals(old)) {
                p.setCheckNumber(newCheckNumber);
                // Save the updated entity
                paymentRepository.save(p);
                break;
            }
        }
    }
    public void updateCheckAmountForCustomer(Integer customerNumber, String checkNumber, BigDecimal amount) {
        Optional<Customers> customers = customerRepository.findById(customerNumber);
        Payments payments = paymentRepository.findById(checkNumber).get();
        if(customers==null || payments==null)
            throw new CustomerNotFoundException("Customer Details not found");
        payments.setAmount(amount);
        paymentRepository.save(payments);
    }
    public List<Payments> getAllPaymentsByDate(Date paymentDate) {
        return paymentRepository.findByPaymentDate(paymentDate);

    }
    public List<Payments> searchPaymentsByCheckNumber(String checkNumber) {
        return paymentRepository.findByCheckNumber(checkNumber);
    }
    public BigDecimal getTotalAmountByCustomerNumber(Integer customerNumber) {
        List<Payments> payments = paymentRepository.findByCustomersCustomerNumber(customerNumber);
        if(payments.isEmpty())
            throw new CustomerNotFoundException("Customer Details not found");
        BigDecimal totalAmount = BigDecimal.ZERO;
        for (Payments payment : payments) {
            totalAmount = totalAmount.add(payment.getAmount());
        }
        return totalAmount;
    }
    public List<Customers> getCustomersByCheckNumber(String checkNumber) {
        List<Payments> payments = paymentRepository.findAll();
        List<Customers> customers = payments.stream()
                .filter(payment -> payment.getCheckNumber().equals(checkNumber))
                .map(Payments::getCustomers)
                .collect(Collectors.toList());
        if(customers.isEmpty())
            throw new CustomerNotFoundException("Customer Details not found");
        return customers;
    }
    public Customers findCustomerWithMaxPaymentAmount() {
        Customers customers = paymentRepository.findCustomerWithMaxPaymentAmount();
        return customers;
    }
    public List<Customers> getCustomersByPaymentDate(Date date) {
        List<Customers> customers = paymentRepository.findByPaymentDate(date)
                .stream()
                .map(Payments::getCustomers)
                .collect(Collectors.toList());
        if(customers.isEmpty())
            throw new CustomerNotFoundException("Payment date not available");
        return customers;
    }
    public Offices getOfficeWithMaxPaymentCollection() {
        return paymentRepository.getOfficeWithMaxPaymentCollection();
    }
    public List<Payments> getPaymentsByCustomer(Integer customerNumber) {
        List<Payments> payments = paymentRepository.findByCustomersCustomerNumber(customerNumber);
        if(payments.isEmpty())
            throw new CustomerNotFoundException("Customer Details not found");
        return payments;
    }
    public List<Payments> getPaymentsByDateRange(Date startDate, Date endDate) {
        return paymentRepository.findByPaymentDateBetween(startDate, endDate);
    }
}
