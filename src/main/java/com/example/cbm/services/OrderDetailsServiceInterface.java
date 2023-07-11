package com.example.cbm.services;
import com.example.cbm.entities.OrderDetails;
import java.math.BigDecimal;
import java.util.List;
public interface OrderDetailsServiceInterface {
    List<OrderDetails> getOrderDetailsForMaxPriceOrder();
    OrderDetails saveOrderDetails(OrderDetails orderDetails);
    void updateQuantityOrdered(Integer order_number, String product_code, Integer newQuantity);
    List<OrderDetails> searchByOrderNumber(Integer order_number);
    BigDecimal getTotalAmountByOrderNumber(Integer orderNumber);
    BigDecimal getTotalSale();
    int getOrderDetailsCountByOrderNumber(int orderNumber);
}
