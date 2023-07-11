package com.example.cbm.entities;
import lombok.*;
import javax.persistence.*;
import javax.validation.constraints.Digits;
import java.util.Objects;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
public class Employees {
    @Id
    @Digits(integer = 10, fraction = 0, message = "Customer Number must be an integer value")
    private Integer employeeNumber;
    @Column
    private String lastName;
    @Column
    private String firstName;
    @Column
    private String extension;
    @Column
    private String email;
    @ManyToOne
    @JoinColumn(name="officeCode")
    private Offices offices;
    @Column
    private Integer reportsTo;
    @Column
    private String jobTitle;
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        Employees other = (Employees) obj;
        return Objects.equals(employeeNumber, other.employeeNumber);
    }
    @Override
    public int hashCode() {
        return Objects.hash(employeeNumber);
    }
}
