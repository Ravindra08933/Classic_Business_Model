package com.example.cbm.services;
import com.example.cbm.entities.OrderDetails;
import com.example.cbm.entities.Products;
import com.example.cbm.exceptions.InsufficientStockException;
import com.example.cbm.exceptions.OrderDetailsNotFoundException;
import com.example.cbm.repositories.OrderDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;
@Service
public class OrderDetailsServiceImpl implements OrderDetailsServiceInterface {
    OrderDetailsRepository orderDetailsRepository;
    @Autowired
    public void setOrderDetailsRepository(OrderDetailsRepository orderDetailsRepository) {
        this.orderDetailsRepository = orderDetailsRepository;
    }
    EntityManager entityManager;
    @Autowired
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
    public List<OrderDetails> getOrderDetailsForMaxPriceOrder() {
        return orderDetailsRepository.getOrderDetailsForMaxPriceOrder();
    }
    public OrderDetails saveOrderDetails(OrderDetails orderDetails)
    {
        Products product = orderDetails.getProduct();
        int orderedQuantity = orderDetails.getQuantityOrdered();
        if(product.getQuantityInStock()<orderedQuantity)
            throw new InsufficientStockException("Insufficient stock");
        return orderDetailsRepository.save(orderDetails);
    }
    @Transactional
    public void updateQuantityOrdered(Integer order_number,String product_code, Integer newQuantity) {
        OrderDetails orderDetails = orderDetailsRepository.findById(order_number).orElseThrow(()->new OrderDetailsNotFoundException("Order Details not found"));
        orderDetails.setQuantityOrdered(newQuantity);
        orderDetailsRepository.save(orderDetails);
    }
    public List<OrderDetails> searchByOrderNumber( Integer order_number) {
        List<OrderDetails> orderDetailsList = orderDetailsRepository.searchByOrderNumber(order_number);
        if (orderDetailsList.isEmpty())
            throw new OrderDetailsNotFoundException("Order Details not found");
        return orderDetailsList;
    }
    public BigDecimal getTotalAmountByOrderNumber(Integer orderNumber) {
        BigDecimal totalAmount = orderDetailsRepository.getTotalAmountByOrderNumber(orderNumber);
        if (totalAmount == null)
            throw new OrderDetailsNotFoundException("OrderDetails not found for orderNumber: " + orderNumber);
        return totalAmount;
    }
    public BigDecimal getTotalSale() {
        BigDecimal totalSale = orderDetailsRepository.getTotalSale();
        return totalSale != null ? totalSale : BigDecimal.ZERO;
    }
    public int getOrderDetailsCountByOrderNumber(int orderNumber) {
        List<OrderDetails> orderDetailsList = orderDetailsRepository.findAll();
        int count = 0;
        for (OrderDetails orderDetails : orderDetailsList) {
            if (orderDetails.getOrder().getOrderNumber() == orderNumber)
                count++;
        }
        return count;
    }
}
