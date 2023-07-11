package com.example.cbm.repositories;
import com.example.cbm.entities.Customers;
import com.example.cbm.entities.Offices;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;
@Repository
public interface OfficeRepository extends JpaRepository<Offices,String> {
    Offices findByOfficeCode(String code);
    List<Offices> findByCityIn(String[] cities);
    @Query("SELECT c FROM Customers c WHERE c.employees.offices.officeCode = :officeCode")
    List<Customers> getCustomersByOfficeCode(String officeCode);
}
