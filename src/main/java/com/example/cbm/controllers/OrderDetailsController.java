package com.example.cbm.controllers;
import com.example.cbm.entities.OrderDetails;
import com.example.cbm.services.OrderDetailsServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
@RestController
@RequestMapping(value = "/api/v1/orderdetails")
public class OrderDetailsController {

    private OrderDetailsServiceInterface orderDetailsServiceInterface;
    @Autowired
    public void setOrderDetailsServiceInterface(OrderDetailsServiceInterface orderDetailsServiceInterface) {
        this.orderDetailsServiceInterface = orderDetailsServiceInterface;
    }

    @GetMapping("/max/price")
    public ResponseEntity<List<OrderDetails>> getOrderDetailsForMaxPriceOrder() {
        List<OrderDetails> orderDetails = orderDetailsServiceInterface.getOrderDetailsForMaxPriceOrder();
        if (!orderDetails.isEmpty()) {
            return new ResponseEntity<>(orderDetails, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @PostMapping(value = "/")
    public ResponseEntity<String> addOrderDetails(@RequestBody OrderDetails orderDetails)
    {
        orderDetailsServiceInterface.saveOrderDetails(orderDetails);
        return new ResponseEntity<String>("Record Created succefully",HttpStatus.CREATED);
    }
    @PutMapping(value="/{order_number}/{product_code}/{quantity_ordered}")
    public ResponseEntity<String> updateQuantityOrdered(
            @PathVariable Integer order_number,@PathVariable String product_code,
            @PathVariable Integer quantity_ordered) {
        try {
            orderDetailsServiceInterface.updateQuantityOrdered(order_number,product_code, quantity_ordered);
            return new ResponseEntity<>("Quantity updated successfully", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/{order_number}")
    public ResponseEntity<List<OrderDetails>> searchByOrderNumber(@PathVariable Integer order_number) {
        List<OrderDetails> orderDetails = orderDetailsServiceInterface.searchByOrderNumber(order_number);
        if (orderDetails.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(orderDetails);
    }
    @GetMapping("/{orderNumber}/total")
    public ResponseEntity<String> getTotalAmountByOrderNumber(@PathVariable("orderNumber") Integer orderNumber) {
        BigDecimal totalAmount = orderDetailsServiceInterface.getTotalAmountByOrderNumber(orderNumber);
        return new ResponseEntity<>("Total amount: " + totalAmount, HttpStatus.OK);
    }
    @GetMapping("/total_sale")
    public ResponseEntity<BigDecimal> getTotalSale() {
        BigDecimal totalSale = orderDetailsServiceInterface.getTotalSale();
        return new ResponseEntity<>(totalSale, HttpStatus.OK);
    }
    @GetMapping("/count/{orderNumber}")
    public int getOrderDetailsCountByOrderNumber(@PathVariable int orderNumber) {
        return orderDetailsServiceInterface.getOrderDetailsCountByOrderNumber(orderNumber);
    }
}
