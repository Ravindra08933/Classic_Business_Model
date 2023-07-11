package com.example.cbm.controllers;
import com.example.cbm.entities.ProductLines;
import com.example.cbm.services.ProductLinesServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping(value = "/api/v1/product_lines")
public class ProductLinesController {

    private ProductLinesServiceInterface productLinesServiceInterface;
    @Autowired
    public void setProductLinesServiceInterface(ProductLinesServiceInterface productLinesServiceInterface) {
        this.productLinesServiceInterface = productLinesServiceInterface;
    }

    @PostMapping(value = "/")
    public ProductLines addProductLines(@RequestBody ProductLines productLines)
    {
        return productLinesServiceInterface.addProductLine(productLines);
    }
    @PutMapping("/{product_line}/text_description/{text_description}")
    public String updateProductLineTextDescription(@PathVariable String product_line, @PathVariable String text_description) {
        productLinesServiceInterface.updateTextDescription(product_line, text_description);
        return "product’s MSRP updated successfully";
    }
    @GetMapping(value = "text_description/{text_description}")
    public ResponseEntity<ProductLines> getById(@PathVariable String text_description)
    {
        return new ResponseEntity<>(productLinesServiceInterface.getProductLines(text_description), HttpStatus.OK);
    }
}

