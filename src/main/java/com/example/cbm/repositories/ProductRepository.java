package com.example.cbm.repositories;
import com.example.cbm.entities.Products;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Products,String> {
    Products findByProductCode(String code);
    Products findByProductName(String name);
    @Query("SELECT SUM(od.quantityOrdered) FROM OrderDetails od WHERE od.product.productCode = :productCode")
    Long getTotalOrderedQuantityForProduct(String productCode);
    @Query("SELECT SUM(od.priceEach * od.quantityOrdered) FROM OrderDetails od WHERE od.product.productCode = :productCode")
    BigDecimal getTotalSaleForProduct(String productCode);
    @Query("SELECT od.product.productCode, SUM(od.priceEach * od.quantityOrdered) FROM OrderDetails od GROUP BY od.product.productCode")
    List<Object[]> getTotalSaleAmountPerProduct();
    @Query("SELECT od.product FROM OrderDetails od WHERE od.quantityOrdered >= :minimumQuantity GROUP BY od.product ORDER BY SUM(od.quantityOrdered) DESC")
    List<Products> getHighlyDemandedProducts(int minimumQuantity);
}

