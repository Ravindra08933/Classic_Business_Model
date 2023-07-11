package com.example.cbm.services;
import com.example.cbm.entities.Products;
import com.example.cbm.exceptions.ProductNotFoundException;
import com.example.cbm.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;
@Service
public class ProductsServiceImpl implements ProductsServiceInterface {
    ProductRepository productRepository;
    @Autowired
    public void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
    EntityManager entityManager;
    @Autowired
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
    public Products addProduct(Products products)
    {
        return productRepository.save(products);
    }
    public void updateBuyPrice(String productCode, BigDecimal newBuyPrice)  {
        Products product = productRepository.findByProductCode(productCode);
        if (product == null) {
            throw new ProductNotFoundException("Product Details not found");
        }
        product.setBuyPrice(newBuyPrice);
        productRepository.save(product);
    }
    public void updateQuantityInStock(String productCode, Short newQuantity) {
        Products product = productRepository.findByProductCode(productCode);
        if (product == null) {
            throw new ProductNotFoundException("Product Details not found");
        }
        product.setQuantityInStock(newQuantity);
        productRepository.save(product);
    }
    public void updateMsrpOfProduct(String productCode, BigDecimal newMsrp) {
        Products product = productRepository.findByProductCode(productCode);
        if (product == null) {
            throw new ProductNotFoundException("Product Details not found");
        }
        product.setMsrp(newMsrp);
        productRepository.save(product);
    }
    public void updateProductVendor(String productCode, String newVendor) {
        Products product = productRepository.findByProductCode(productCode);
        if (product == null) {
            throw new ProductNotFoundException("Product Details not found");
        }
        product.setProductVendor(newVendor);
        productRepository.save(product);
    }
    public void updateProductScale(String productCode, String newScale) {
        Products product = productRepository.findByProductCode(productCode);
        if (product == null) {
            throw new ProductNotFoundException("Product Details not found");
        }
        product.setProductScale(newScale);
        productRepository.save(product);
    }
    public void updateProductName(String productCode, String newName) {
        Products product = productRepository.findByProductCode(productCode);
        if (product == null) {
            throw new ProductNotFoundException("Product Details not found");
        }
        product.setProductName(newName);
        productRepository.save(product);
    }
    public Products getProduct(String code)
    {
        Products product = productRepository.findByProductCode(code);
        if (product == null)
            throw new ProductNotFoundException("Product Details not found");
        return product;
    }
    public Products getProductByName(String productName) {
        Products product = productRepository.findByProductName(productName);
        if (product == null)
            throw new ProductNotFoundException("Product Details not found");
        return product;
    }
    public List<Products> searchByProductScale(String productScale) {
        List<Products> productList = productRepository.findAll();
        List<Products> products = productList.stream()
                .filter(product -> product.getProductScale().equalsIgnoreCase(productScale))
                .collect(Collectors.toList());
        if(products.isEmpty())
            throw new ProductNotFoundException("Product Details not found");
        return products;
    }
    public List<Products> searchByProductVendor(String productVendor) {
        List<Products> productList = productRepository.findAll();
        List<Products> productsLists = productList.stream()
                .filter(product -> product.getProductScale().equalsIgnoreCase(productVendor))
                .collect(Collectors.toList());
        if(productsLists.isEmpty())
            throw new ProductNotFoundException("Product Details not found");
        return productsLists;

    }
    public Long getTotalOrderedQuantityForProduct(String productCode) {
        Products product = productRepository.findByProductCode(productCode);
        if (product == null)
            throw new ProductNotFoundException("Product Details not found");
        Long totalOrderedQuantity = productRepository.getTotalOrderedQuantityForProduct(productCode);
        return totalOrderedQuantity != null ? totalOrderedQuantity : 0L;
    }
    public BigDecimal getTotalSaleForProduct(String productCode) {
        Products product = productRepository.findByProductCode(productCode);
        if (product == null)
            throw new ProductNotFoundException("Product Details not found");
        BigDecimal totalSale = productRepository.getTotalSaleForProduct(productCode);
        return (totalSale != null) ? totalSale : BigDecimal.ZERO;
    }
    public List<Object[]> getTotalSaleAmountPerProduct() {
        return productRepository.getTotalSaleAmountPerProduct();
    }
    public List<Products> getHighlyDemandedProducts(int minimumQuantity) {
        return productRepository.getHighlyDemandedProducts(minimumQuantity);
    }
}
