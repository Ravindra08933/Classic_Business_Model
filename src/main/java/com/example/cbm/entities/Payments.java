package com.example.cbm.entities;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
@Setter
@Getter
@NoArgsConstructor
@ToString
@AllArgsConstructor
@Entity
@Table(name = "payments")
public class Payments {
    @Id
    @GeneratedValue(generator = "randomStringGenerator")
    @GenericGenerator(name = "randomStringGenerator", strategy = "com.example.cbm.controllers.RandomStringGenerator")
    @Column(name = "checkNumber", length = 4)
    private String checkNumber;
    @ManyToOne
    @JoinColumn(name = "customerNumber")
    private Customers customers;
    @Column
    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
    private Date paymentDate;
    @Column
    private BigDecimal amount;
}
