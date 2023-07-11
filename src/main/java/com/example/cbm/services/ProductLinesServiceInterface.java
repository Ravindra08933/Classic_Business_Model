package com.example.cbm.services;
import com.example.cbm.entities.ProductLines;
public interface ProductLinesServiceInterface {
    ProductLines addProductLine(ProductLines productLines);
    void updateTextDescription(String productLine, String newTextDescription);
    ProductLines getProductLines(String name);
}

