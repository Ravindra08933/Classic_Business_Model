package com.example.cbm.repositories;
import com.example.cbm.dtos.CustomerOrderPaymentDetails;
import com.example.cbm.entities.Customers;
import com.example.cbm.entities.Payments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;
@Repository
public interface CustomerRepository extends JpaRepository<Customers,Integer> {
    @Query("SELECT c FROM Customers c WHERE c.employees.employeeNumber = :employeeNumber")
    List<Customers> findBySalesRepEmployeeNumber(Integer employeeNumber);
    @Query("SELECT p FROM Payments p WHERE p.customers.customerNumber = :customerNumber")
    public List<Payments> getPaymentDetailsForCustomer(Integer customerNumber);
    @Query("SELECT od, p, o, e FROM OrderDetails od JOIN od.product p JOIN od.order o JOIN o.customers c JOIN c.employees e WHERE c.customerNumber = :customerNumber")
    public Collection<Object[]> getOrderDetailsForCustomer(Integer customerNumber);
    @Query("SELECT o, p FROM Orders o JOIN o.customers c JOIN Payments p ON c.customerNumber = p.customers.customerNumber WHERE c.customerName = :customerName")
    public Collection<CustomerOrderPaymentDetails> getOrderAndPaymentDetailsForCustomer(String customerName);
    List<Customers> findByCustomerName(String customerName);
    List<Customers> findByCity(String city);
    Customers findByCustomerNumber(Integer customerNumber);
    List<Customers> findByCountry(String country);
    Customers findByPhone(String phone);
    List<Customers> findByContactLastName(String lastName);
    List<Customers> findByPostalCode(String postalCode);
    List<Customers> findByCreditLimit(BigDecimal creditLimit);
}
