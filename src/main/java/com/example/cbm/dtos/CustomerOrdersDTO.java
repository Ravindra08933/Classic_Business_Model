package com.example.cbm.dtos;

import com.example.cbm.entities.Customers;
import com.example.cbm.entities.Orders;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CustomerOrdersDTO {
    private Customers customer;
    private List<Orders> orders;
}
