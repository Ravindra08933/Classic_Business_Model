package com.example.cbm.controllers;
import com.example.cbm.dtos.CustomerOrdersDTO;
import com.example.cbm.dtos.CustomerOrdersResponse;
import com.example.cbm.entities.Customers;
import com.example.cbm.entities.Orders;
import com.example.cbm.entities.Products;
import com.example.cbm.exceptions.CustomerNotFoundException;
import com.example.cbm.repositories.CustomerRepository;
import com.example.cbm.services.OrderServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Date;
import java.util.List;
@RestController
@RequestMapping(value = "/api/v1/orders")
public class OrderController {

    private OrderServiceInterface orderServiceInterface;
    @Autowired
    public void setOrderServiceInterface(OrderServiceInterface orderServiceInterface) {
        this.orderServiceInterface = orderServiceInterface;
    }
    private CustomerRepository customerRepository;
    @Autowired
    public void setCustomerRepository(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }
    @PostMapping(value = "/")
    public ResponseEntity<String> addOrder(@RequestBody Orders orders)
    {
        orderServiceInterface.saveOrders(orders);
        return new ResponseEntity<String>("Order Created successfully", HttpStatus.CREATED);
    }
    @PutMapping("/{order_number}/{shipped_date}")
    public ResponseEntity<String> updateOrderShippedDate(
            @PathVariable Integer order_number,
            @PathVariable("shipped_date") @DateTimeFormat(pattern = "yyyy-MM-dd") Date shipped_date
    ) {
        orderServiceInterface.updateOrderShippedDate(order_number, shipped_date);
        return new ResponseEntity<>("Order shipped date updated successfully", HttpStatus.OK);
    }
    @PutMapping("/{order_number}")
    public ResponseEntity<String> updateOrderStatusDate(
            @PathVariable Integer order_number,
            @RequestParam String status_date
    ) {
        orderServiceInterface.updateOrderStatusDate(order_number, status_date);
        return new ResponseEntity<>("Order shipped date updated successfully", HttpStatus.OK);
    }
    @GetMapping("/orderCustomerDetails/{customerNumber}")
    public ResponseEntity<CustomerOrdersDTO> getAllOrdersByCustomer(@PathVariable Integer customerNumber) {
        CustomerOrdersDTO customerOrdersDTO = orderServiceInterface.getAllOrdersByCustomer(customerNumber);
        Customers customer = customerRepository.findById(customerNumber).orElse(null);
        return new ResponseEntity<>(customerOrdersDTO, HttpStatus.OK);
    }
    @GetMapping(value = "/getOrder/{order_number}")
    public ResponseEntity<Orders> getOrder(@PathVariable Integer order_number)
    {
        Orders order = orderServiceInterface.getOrderDetails(order_number);
        return new ResponseEntity<Orders>(order, HttpStatus.OK);
    }
    @GetMapping(value = "/{order_date}")
    public ResponseEntity<List<Orders>> getOrderByOrderDate(@PathVariable Date order_date)
    {
        List<Orders> order = orderServiceInterface.getAllOrdersByOrderDate(order_date);
        return new ResponseEntity<List<Orders>>(order, HttpStatus.OK);
    }
    @GetMapping(value = "/{required_date}")
    public ResponseEntity<List<Orders>> getOrderByRequiredDate(@PathVariable Date required_date)
    {
        List<Orders> order = orderServiceInterface.getAllOrdersByRequiredDate(required_date);
        return new ResponseEntity<List<Orders>>(order, HttpStatus.OK);
    }
    @GetMapping(value = "/{shipped_date}")
    public ResponseEntity<List<Orders>> getOrderByShippedDate(@PathVariable Date shipped_date)
    {
        List<Orders> order = orderServiceInterface.getAllOrdersByShippedDate(shipped_date);
        return new ResponseEntity<List<Orders>>(order, HttpStatus.OK);
    }
    @GetMapping(value = "/{status}")
    public ResponseEntity<List<Orders>> getOrderByStatus(@PathVariable String status)
    {
        List<Orders> order = orderServiceInterface.getAllOrdersByStatus(status);
        return new ResponseEntity<List<Orders>>(order, HttpStatus.OK);
    }
    @GetMapping("/{customer_number}/{status}")
    public ResponseEntity<CustomerOrdersResponse> getOrdersByStatusAndCustomer(
            @PathVariable Integer customer_number,
            @PathVariable String status
    ) {

        Customers customer = customerRepository.findById(customer_number).get();
        if(customer==null)
        {
            throw new CustomerNotFoundException("Customer Details not found");
        }
        List<Orders> orders = orderServiceInterface.getOrdersByStatusAndCustomer(status, customer_number);
        CustomerOrdersResponse response = new CustomerOrdersResponse(customer, orders);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @GetMapping("/{order_id}/products")
    public ResponseEntity<List<Products>> getProductDetailsForOrder(@PathVariable Integer order_id) {
        List<Products> products = orderServiceInterface.getProductDetailsForOrder(order_id);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }
    @GetMapping("/{order_id}/product_names")
    public ResponseEntity<List<String>> getProductNamesForOrderNumber(@PathVariable Integer order_id) {
        List<String> productNames = orderServiceInterface.getProductNamesForOrderNumber(order_id);
        return new ResponseEntity<>(productNames, HttpStatus.OK);
    }
    @GetMapping("/products")
    public ResponseEntity<List<Products>> getAllProductDetailsForAllOrders() {
        List<Products> productDetails = orderServiceInterface.getAllProductDetailsForAllOrders();
        if (!productDetails.isEmpty()) {
            return new ResponseEntity<>(productDetails, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/{order_status}/orders")
    public ResponseEntity<List<Orders>> getDeliveredOrdersWithSameDeliveryDate(@PathVariable String order_status) {
        List<Orders> orders = orderServiceInterface.getDeliveredOrdersWithSameDeliveryDate(order_status);
        if (!orders.isEmpty()) {
            return new ResponseEntity<>(orders, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/product_and_product_line_details")
    public ResponseEntity<List<Products>> getProductsByShipmentDate() {
        List<Products> products = orderServiceInterface.getProductsByShipmentDate();
        if (!products.isEmpty()) {
            return new ResponseEntity<>(products, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}

