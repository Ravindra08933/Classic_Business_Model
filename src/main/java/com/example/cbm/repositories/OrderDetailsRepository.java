package com.example.cbm.repositories;
import com.example.cbm.entities.OrderDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.math.BigDecimal;
import java.util.List;
@Repository
public interface OrderDetailsRepository extends JpaRepository<OrderDetails, Integer> {
    @Query("SELECT od FROM OrderDetails od WHERE od.priceEach = (SELECT MAX(od2.priceEach) FROM OrderDetails od2)")
    List<OrderDetails> getOrderDetailsForMaxPriceOrder();
    @Query("SELECT od FROM OrderDetails od WHERE od.order.orderNumber = :order_number")
    List<OrderDetails> searchByOrderNumber( Integer order_number);
    @Query("SELECT SUM(od.quantityOrdered * od.priceEach) FROM OrderDetails od WHERE od.order.orderNumber = :orderNumber")
    BigDecimal getTotalAmountByOrderNumber(Integer orderNumber);
    @Query("SELECT SUM(od.quantityOrdered * od.priceEach) FROM OrderDetails od")
    BigDecimal getTotalSale();
}
