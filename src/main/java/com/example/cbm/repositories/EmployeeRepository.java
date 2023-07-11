package com.example.cbm.repositories;
import com.example.cbm.entities.Employees;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
@Repository
public interface EmployeeRepository extends JpaRepository<Employees,Integer> {
    List<Employees> findByOfficesOfficeCode(String officeCode);
    List<Employees> findByOfficesCity(String city);
}
