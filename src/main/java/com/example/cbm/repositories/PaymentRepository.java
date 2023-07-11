package com.example.cbm.repositories;
import com.example.cbm.entities.Customers;
import com.example.cbm.entities.Offices;
import com.example.cbm.entities.Payments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
@Repository
public interface PaymentRepository extends JpaRepository<Payments,String> {
    List<Payments> findByCustomers_CustomerNumber(Integer customerNumber);
    List<Payments> findByCheckNumber(String checkNumber);
    List<Payments> findByPaymentDate(Date paymentDate);
    List<Payments> findByCustomersCustomerNumber(Integer customerNumber);
    List<Payments> findByPaymentDateBetween(Date startDate, Date endDate);
    @Query("SELECT p.customers FROM Payments p WHERE p.amount = (SELECT MAX(p.amount) FROM Payments p)")
    Customers findCustomerWithMaxPaymentAmount();
    @Query("SELECT p.customers.employees.offices.officeCode, SUM(p.amount) FROM Payments p GROUP BY p.customers.employees.offices.officeCode ORDER BY SUM(p.amount) DESC")
    Offices getOfficeWithMaxPaymentCollection();
}
