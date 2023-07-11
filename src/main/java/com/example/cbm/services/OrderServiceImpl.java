package com.example.cbm.services;
import com.example.cbm.dtos.CustomerOrdersDTO;
import com.example.cbm.entities.Customers;
import com.example.cbm.entities.Orders;
import com.example.cbm.entities.Products;
import com.example.cbm.exceptions.CustomerNotFoundException;
import com.example.cbm.exceptions.OrderDetailsNotFoundException;
import com.example.cbm.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.Date;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderServiceInterface {
    OrderRepository orderRepository;
    @Autowired
    public void setOrderRepository(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }
    EntityManager entityManager;
    @Autowired
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
    public Orders saveOrders(Orders orders)
    {
        return orderRepository.save(orders);
    }
    public Orders updateOrderShippedDate(Integer orderNumber, Date shippedDate) {
        Orders orders = orderRepository.findById(orderNumber).orElseThrow(()->new OrderDetailsNotFoundException("Order Details not found"));
        orders.setShippedDate(shippedDate);
        orderRepository.save(orders);
        return orders;
    }
    public Orders updateOrderStatusDate(Integer orderNumber, String statusdate) {
        Orders orders = orderRepository.findById(orderNumber).orElseThrow(()->new OrderDetailsNotFoundException("Order Details not found"));
        orders.setStatus(statusdate);
        orderRepository.save(orders);
        return orders;
    }
    public CustomerOrdersDTO getAllOrdersByCustomer(Integer customerNumber) {
        List<Orders> orders = orderRepository.getAllOrdersByCustomer(customerNumber);
        if(orders.isEmpty())
            throw new CustomerNotFoundException("Customer Details not found");
        Customers customer = entityManager.find(Customers.class, customerNumber);
        CustomerOrdersDTO customerOrdersDTO = new CustomerOrdersDTO();
        customerOrdersDTO.setCustomer(customer);
        customerOrdersDTO.setOrders(orders);
        return customerOrdersDTO;
    }
    public Orders getOrderDetails(Integer orderNumber)
    {
        return orderRepository.findById(orderNumber).orElseThrow(()->new OrderDetailsNotFoundException("Order Details not found"));
    }
    public List<Orders> getAllOrdersByOrderDate(Date date)
    {
        return orderRepository.findByOrderDate(date);
    }
    public List<Orders> getAllOrdersByRequiredDate(Date date)
    {
        return orderRepository.findByRequiredDate(date);
    }
    public List<Orders> getAllOrdersByShippedDate(Date shippedDate) {
        return orderRepository.findByShippedDate(shippedDate);
    }
    public List<Orders> getAllOrdersByStatus(String status) {
        return orderRepository.findByStatus(status);
    }
    public List<Orders> getOrdersByStatusAndCustomer(String orderStatus, Integer customerNumber) {
        return orderRepository.getOrdersByStatusAndCustomer(orderStatus,customerNumber);
    }
    public List<Products> getProductDetailsForOrder(Integer orderNumber) {
        List<Products> products = orderRepository.getProductDetailsForOrder(orderNumber);
        if(products.isEmpty())
            throw new OrderDetailsNotFoundException("Order Details not found");
        return products;
    }
    public List<String> getProductNamesForOrderNumber(Integer orderNumber) {
        List<String> productNames =  orderRepository.getProductNamesForOrderNumber(orderNumber);
        if(productNames.isEmpty())
            throw new OrderDetailsNotFoundException("Order Details not found");
        return productNames;
    }
    public List<Products> getAllProductDetailsForAllOrders() {
        return orderRepository.getAllProductDetailsForAllOrders();
    }
    public List<Orders> getDeliveredOrdersWithSameDeliveryDate(String order_status) {
        return orderRepository.getDeliveredOrdersWithSameDeliveryDate(order_status);
    }
    public List<Products> getProductsByShipmentDate() {
        return orderRepository.getProductsByShipmentDate();
    }
}
