package com.example.cbm.services;
import com.example.cbm.entities.Products;
import java.math.BigDecimal;
import java.util.List;
public interface ProductsServiceInterface {
    Products addProduct(Products products);
    void updateBuyPrice(String productCode, BigDecimal newBuyPrice);
    void updateQuantityInStock(String productCode, Short newQuantity);
    void updateMsrpOfProduct(String productCode, BigDecimal newMsrp);
    void updateProductVendor(String productCode, String newVendor);
    void updateProductScale(String productCode, String newScale);
    void updateProductName(String productCode, String newName);
    Products getProduct(String code);
    Products getProductByName(String productName);
    List<Products> searchByProductScale(String productScale);
    List<Products> searchByProductVendor(String productVendor);
    Long getTotalOrderedQuantityForProduct(String productCode);
    BigDecimal getTotalSaleForProduct(String productCode);
    List<Object[]> getTotalSaleAmountPerProduct();
    List<Products> getHighlyDemandedProducts(int minimumQuantity);
}
