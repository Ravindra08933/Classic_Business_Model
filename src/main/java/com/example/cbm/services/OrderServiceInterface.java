package com.example.cbm.services;
import com.example.cbm.dtos.CustomerOrdersDTO;
import com.example.cbm.entities.Orders;
import com.example.cbm.entities.Products;
import com.example.cbm.exceptions.CustomerNotFoundException;
import com.example.cbm.exceptions.OrderDetailsNotFoundException;
import com.example.cbm.entities.Customers;
import java.util.Date;
import java.util.List;
public interface OrderServiceInterface {
    Orders saveOrders(Orders orders);
    Orders updateOrderShippedDate(Integer orderNumber, Date shippedDate);
    Orders updateOrderStatusDate(Integer orderNumber, String statusdate);
    CustomerOrdersDTO getAllOrdersByCustomer(Integer customerNumber) throws CustomerNotFoundException;
    Orders getOrderDetails(Integer orderNumber) throws OrderDetailsNotFoundException;
    List<Orders> getAllOrdersByOrderDate(Date date);
    List<Orders> getAllOrdersByRequiredDate(Date date);
    List<Orders> getAllOrdersByShippedDate(Date shippedDate);
    List<Orders> getAllOrdersByStatus(String status);
    List<Orders> getOrdersByStatusAndCustomer(String orderStatus,Integer customerNumber);
    List<Products> getProductDetailsForOrder(Integer orderNumber) throws OrderDetailsNotFoundException;
    List<String> getProductNamesForOrderNumber(Integer orderNumber) throws OrderDetailsNotFoundException;
    List<Products> getAllProductDetailsForAllOrders();
    List<Orders> getDeliveredOrdersWithSameDeliveryDate(String order_status);
    List<Products> getProductsByShipmentDate();
}
