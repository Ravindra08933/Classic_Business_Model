package com.example.cbm.services;
import com.example.cbm.dtos.EmployeeOfficeDTO;
import com.example.cbm.entities.Employees;
import java.util.List;
public interface EmployeeServiceInterface {
    Employees addEmployee(Employees employees);
    Employees updateEmployeeHierarchy(Integer employee_number, Integer new_Employee_Number);
    Employees updateEmployeeRoll(Integer employee_no, String role);
    EmployeeOfficeDTO assignOfficeToEmployee(String officeCode, Integer employeeNumber);
    Employees getEmployeeByNumber(Integer cid);
    List<Employees> getAllEmployees();
    List<Employees> getAllEmployeesByOfficeCode(String officeCode);
    List<Employees> getEmployeesByCity(String city);
}

