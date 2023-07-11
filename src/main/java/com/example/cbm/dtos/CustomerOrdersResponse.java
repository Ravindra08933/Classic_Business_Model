package com.example.cbm.dtos;
import com.example.cbm.entities.Customers;
import com.example.cbm.entities.Orders;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import java.util.List;
@Setter
@Getter
@AllArgsConstructor
public class CustomerOrdersResponse {
    private Customers customer;
    private List<Orders> orders;
}

