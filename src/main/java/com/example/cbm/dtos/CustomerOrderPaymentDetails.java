package com.example.cbm.dtos;

import com.example.cbm.entities.Orders;
import com.example.cbm.entities.Payments;
public class CustomerOrderPaymentDetails {
    private Orders order;
    private Payments payment;
    public Orders getOrder() {
        return order;
    }
    public void setOrder(Orders order) {
        this.order = order;
    }

    public CustomerOrderPaymentDetails() {
    }

    public Payments getPayment() {
        return payment;
    }
    public void setPayment(Payments payment) {
        this.payment = payment;
    }
    public CustomerOrderPaymentDetails(Orders order, Payments payment) {
        this.order = order;
        this.payment = payment;
    }
}

