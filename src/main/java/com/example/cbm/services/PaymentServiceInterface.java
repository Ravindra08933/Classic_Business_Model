package com.example.cbm.services;
import com.example.cbm.entities.Customers;
import com.example.cbm.entities.Offices;
import com.example.cbm.entities.Payments;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
public interface PaymentServiceInterface {
    Payments savePayment(Payments payments, Customers customers);
    void updateCheckNumberForCustomer(Integer customerNumber, String old, String newCheckNumber);
    void updateCheckAmountForCustomer(Integer customerNumber, String checkNumber, BigDecimal amount);
    List<Payments> getAllPaymentsByDate(Date paymentDate);
    List<Payments> searchPaymentsByCheckNumber(String checkNumber);
    BigDecimal getTotalAmountByCustomerNumber(Integer customerNumber);
    List<Customers> getCustomersByCheckNumber(String checkNumber);
    Customers findCustomerWithMaxPaymentAmount();
    List<Customers> getCustomersByPaymentDate(Date date);
    Offices getOfficeWithMaxPaymentCollection();
    List<Payments> getPaymentsByCustomer(Integer customerNumber);
    List<Payments> getPaymentsByDateRange(Date startDate, Date endDate);
}
