package com.example.cbm.dtos;
import com.example.cbm.entities.Employees;
import com.example.cbm.entities.Offices;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeOfficeDTO {
    private Employees employee;
    private Offices office;
}