package com.example.cbm.controllers;
import com.example.cbm.entities.Products;
import com.example.cbm.services.ProductsServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.math.BigDecimal;
import java.util.List;
@RestController
@RequestMapping(value = "/api/v1/products")
public class ProductsController {

    private ProductsServiceInterface productsServiceInterface;
    @Autowired
    public void setProductsServiceInterface(ProductsServiceInterface productsServiceInterface) {
        this.productsServiceInterface = productsServiceInterface;
    }

    @PostMapping(value = "/")
    public ResponseEntity<String> saveProducts(@RequestBody Products products)
    {
        productsServiceInterface.addProduct(products);
        return new ResponseEntity<String>("product details added successfully", HttpStatus.CREATED);
    }
    @PutMapping("/{poduct_code}/{buy_price}")
    public ResponseEntity<String> updateBuyPrice(
            @PathVariable("product_code") String productCode,
            @PathVariable("buy_price") BigDecimal buy_price) {

        productsServiceInterface.updateBuyPrice(productCode, buy_price);
        return new ResponseEntity<>("product’s buy price updated successfully", HttpStatus.OK);
    }
    @PutMapping("/{product_code}/{quantity_in_stock}")
    public ResponseEntity<String> updateQuantityInStock(
            @PathVariable("product_code") String product_code,
            @RequestParam("quantity_in_stock") Short quantity_in_stock) {
        productsServiceInterface.updateQuantityInStock(product_code, quantity_in_stock);
        return ResponseEntity.ok("product’s quantity updated successfully");
    }
    @PutMapping("/{product_code}/{msrp}")
    public ResponseEntity<String> updateMsrp(
            @PathVariable("product_code") String product_code,
            @RequestParam("msrp") BigDecimal newMsrp) {
        productsServiceInterface.updateMsrpOfProduct(product_code, newMsrp);
        return ResponseEntity.ok("product’s MSRP updated successfully");
    }
    @PutMapping("/{product_code}/{product_vendor}")
    public ResponseEntity<String> updateProductVendor(
            @PathVariable("product_code") String product_code,
            @PathVariable("product_vendor") String product_vendor) {
        productsServiceInterface.updateProductVendor(product_code, product_vendor);
        return new ResponseEntity<>("Product vendor updated successfully", HttpStatus.OK);
    }
    @PutMapping("/{product_code}/{product_scale}")
    public ResponseEntity<String> updateProductScale(
            @PathVariable("product_code") String product_code,
            @PathVariable("product_scale") String product_scale) {

        productsServiceInterface.updateProductScale(product_code, product_scale);
        return new ResponseEntity<>("Product scale updated successfully", HttpStatus.OK);
    }
    @PutMapping("/{product_code}/{product_name}")
    public ResponseEntity<String> updateProductName(
            @PathVariable("productCode") String product_code,
            @PathVariable("product_name") String product_name) {

        productsServiceInterface.updateProductName(product_code, product_name);
        return new ResponseEntity<>("Product name updated successfully", HttpStatus.OK);
    }
    @GetMapping(value = "{product_code}")
    public ResponseEntity<Products> getProductByProductCode(@PathVariable String product_code)
    {
        Products products = productsServiceInterface.getProduct(product_code);
        return new ResponseEntity<Products>(products,HttpStatus.OK);
    }
    @GetMapping(value = "{product_name}")
    public ResponseEntity<Products> getProductByName(@PathVariable String product_name)
    {
        Products products = productsServiceInterface.getProductByName(product_name);
        return new ResponseEntity<Products>(products,HttpStatus.OK);
    }
    @GetMapping("/search/{product_scale}")
    public ResponseEntity<List<Products>> searchProductsByScale(@PathVariable String product_scale) {
        List<Products> products = productsServiceInterface.searchByProductScale(product_scale);
        if (!products.isEmpty()) {
            return new ResponseEntity<>(products, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/search/{product_vendor}")
    public ResponseEntity<List<Products>> searchProductsByVendor(@PathVariable String product_vendor) {
        List<Products> products = productsServiceInterface.searchByProductVendor(product_vendor);
        if (!products.isEmpty()) {
            return new ResponseEntity<>(products, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/{product_code}/total_ordered_qty")
    public Long getTotalOrderedQuantityForProduct(@PathVariable String product_code) {
        return productsServiceInterface.getTotalOrderedQuantityForProduct(product_code);
    }
    @GetMapping("/{product_code}/total_sale")
    public ResponseEntity<BigDecimal> getTotalSaleForProduct(@PathVariable String product_code) {
        BigDecimal totalSale = productsServiceInterface.getTotalSaleForProduct(product_code);
        return new ResponseEntity<>(totalSale, HttpStatus.OK);
    }
    @GetMapping("/total_sale_amount")
    public ResponseEntity<List<Object[]>> getTotalSaleAmountPerProduct() {
        try {
            List<Object[]> result = productsServiceInterface.getTotalSaleAmountPerProduct();
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/product_details")
    public List<Products> getHighlyDemandedProducts(@RequestParam int minimumQuantity) {
        return productsServiceInterface.getHighlyDemandedProducts(minimumQuantity);
    }
}
