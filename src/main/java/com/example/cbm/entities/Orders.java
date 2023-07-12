package com.example.cbm.entities;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.hibernate.annotations.Type;
import javax.persistence.*;
import javax.validation.constraints.Digits;
import java.util.Date;
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name="orders")
public class Orders {
    @Id
    @Digits(integer = 10, fraction = 0, message = "Customer Number must be an integer value")
    private Integer orderNumber;
    @Column
    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
    private Date orderDate;
    @Column
    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
    private Date requiredDate;
    @Column
    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
    private Date shippedDate;
    @Column
    private String status;
    @Column
    @Type(type = "text")
    private String comments;
    @ManyToOne
    @JoinColumn(name = "customerNumber")
    private Customers customers;
}
