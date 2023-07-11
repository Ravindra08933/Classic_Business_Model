package com.example.cbm.repositories;
import com.example.cbm.entities.Orders;
import com.example.cbm.entities.Products;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.Date;
import java.util.List;
@Repository
public interface OrderRepository extends JpaRepository<Orders, Integer> {
    List<Orders> findByOrderDate(Date date);
    List<Orders> findByRequiredDate(Date date);
    List<Orders> findByShippedDate(Date date);
    List<Orders> findByStatus(String status);
    @Query("SELECT o FROM Orders o WHERE o.status = :orderStatus AND o.customers.customerNumber = :customerNumber")
    List<Orders> getOrdersByStatusAndCustomer(String orderStatus, Integer customerNumber);
    @Query("SELECT od.product FROM OrderDetails od WHERE od.order.orderNumber = :orderNumber")
    List<Products> getProductDetailsForOrder(Integer orderNumber);
    @Query("SELECT od.product.productName FROM OrderDetails od WHERE od.order.orderNumber = :orderNumber")
    List<String> getProductNamesForOrderNumber(Integer orderNumber);
    @Query("SELECT o FROM Orders o WHERE o.status = :order_status AND o.shippedDate IS NOT NULL AND o.shippedDate = o.orderDate")
    List<Orders> getDeliveredOrdersWithSameDeliveryDate(String order_status);
    @Query("SELECT od.product FROM OrderDetails od WHERE od.order.shippedDate = 2023-01-01")
    List<Products> getProductsByShipmentDate();
    @Query("SELECT od.product FROM OrderDetails od")
    List<Products> getAllProductDetailsForAllOrders();
    @Query("SELECT o FROM Orders o WHERE o.customers.customerNumber = :customerNumber")
    List<Orders> getAllOrdersByCustomer(Integer customerNumber);
}
