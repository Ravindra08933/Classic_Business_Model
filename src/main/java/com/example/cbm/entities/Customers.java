package com.example.cbm.entities;

import lombok.*;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "customers")
@Entity
public class Customers {
    @Id
    private Integer customerNumber;
    @Column
    private String customerName;
    @Column
    private String contactLastName;
    @Column
    private String contactFirstName;
    @Column
    private String phone;
    @Column
    private String addressLine1;
    @Column
    private String addressLine2;
    @Column
    private String city;
    @Column
    private String state;
    @Column
    private String postalCode;
    @Column
    private String country;
    @ManyToOne
    @JoinColumn(name = "salesRepEmployeeNumber", referencedColumnName = "employeeNumber")
    private Employees employees;
    @Column
    private BigDecimal creditLimit;
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        Customers other = (Customers) obj;
        return Objects.equals(customerNumber, other.customerNumber);
    }
    @Override
    public int hashCode() {
        return Objects.hash(customerNumber);
    }
}
