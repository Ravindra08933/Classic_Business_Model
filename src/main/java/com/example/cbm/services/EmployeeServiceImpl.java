package com.example.cbm.services;
import com.example.cbm.dtos.EmployeeOfficeDTO;
import com.example.cbm.entities.Employees;
import com.example.cbm.entities.Offices;
import com.example.cbm.exceptions.EmployeeNotFoundException;
import com.example.cbm.exceptions.OfficeNotFoundException;
import com.example.cbm.repositories.EmployeeRepository;
import com.example.cbm.repositories.OfficeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeServiceInterface {
    private EmployeeRepository employeeRepository;
    @Autowired
    public void setEmployeeRepository(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }
    OfficeRepository officeRepository;
    @Autowired
    public void setOfficeRepository(OfficeRepository officeRepository) {
        this.officeRepository = officeRepository;
    }
    public Employees addEmployee(Employees employees)
    {
        return employeeRepository.save(employees);
    }
    public Employees updateEmployeeHierarchy(Integer employee_number,Integer new_Employee_Number)
    {
        Employees employees = employeeRepository.findById(employee_number).orElseThrow(()->new EmployeeNotFoundException("Employee details not found"));
        employees.setReportsTo(new_Employee_Number);
        employeeRepository.save(employees);
        return employees;
    }
    public Employees updateEmployeeRoll(Integer employee_no,String role)
    {
        Employees employees1 = employeeRepository.findById(employee_no).orElseThrow(()->new EmployeeNotFoundException("Employee details not found"));
        employees1.setJobTitle(role);
        employeeRepository.save(employees1);
        return employees1;
    }
    public EmployeeOfficeDTO assignOfficeToEmployee(String officeCode, Integer employeeNumber) {
        Offices office = officeRepository.findByOfficeCode(officeCode);
        Employees employees = employeeRepository.findById(employeeNumber).orElseThrow(()->new EmployeeNotFoundException("Employee details not found"));
        if (office == null)
            throw new OfficeNotFoundException("Employee details not found");
        employees.setOffices(office);
        employeeRepository.save(employees);
        EmployeeOfficeDTO employeeOfficeDTO = new EmployeeOfficeDTO(employees,office);
        return employeeOfficeDTO;
    }
    public Employees getEmployeeByNumber(Integer cid)
    {

        Employees employees1 = employeeRepository.findById(cid).orElseThrow(()->new EmployeeNotFoundException("Employee details not found"));
        return employees1;
    }
    public List<Employees> getAllEmployees()
    {
        List<Employees> employees = employeeRepository.findAll();
        if(employees.isEmpty())
            throw new EmployeeNotFoundException("Employee details not found");
        return employees;
    }
    public List<Employees> getAllEmployeesByOfficeCode(String officeCode) {

        List<Employees> employees = employeeRepository.findByOfficesOfficeCode(officeCode);
        if(employees.isEmpty())
            throw new EmployeeNotFoundException("Employee details not found");
        return employees;
    }
    public List<Employees> getEmployeesByCity(String city) {
        List<Employees> employees = employeeRepository.findByOfficesCity(city);
        if(employees.isEmpty())
            throw new EmployeeNotFoundException("Employee details not found");
        return employees;
    }
}
