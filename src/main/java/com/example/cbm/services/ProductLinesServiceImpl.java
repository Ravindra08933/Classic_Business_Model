package com.example.cbm.services;
import com.example.cbm.entities.ProductLines;
import com.example.cbm.exceptions.ProductNotFoundException;
import com.example.cbm.repositories.ProductLinesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductLinesServiceImpl implements ProductLinesServiceInterface {
    ProductLinesRepository productLinesRepository;
    @Autowired
    public void setProductLinesRepository(ProductLinesRepository productLinesRepository) {
        this.productLinesRepository = productLinesRepository;
    }
    public ProductLines addProductLine(ProductLines productLines)
    {
        return productLinesRepository.save(productLines);
    }
    public void updateTextDescription(String productLine, String newTextDescription) {
        ProductLines productLineEntity = productLinesRepository.findByProductLine(productLine);
        if (productLineEntity == null)
            throw new ProductNotFoundException("Product Details not found");
        productLineEntity.setTextDescription(newTextDescription);
        productLinesRepository.save(productLineEntity);
    }
    public ProductLines getProductLines(String name) {
        ProductLines productLines = productLinesRepository.findByProductLine(name);
        if(productLines == null)
            throw new ProductNotFoundException("Product Details not found");
        return productLines;
    }
}
