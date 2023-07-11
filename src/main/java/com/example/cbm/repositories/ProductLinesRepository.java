package com.example.cbm.repositories;
import com.example.cbm.entities.ProductLines;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface ProductLinesRepository extends JpaRepository<ProductLines,String> {
    ProductLines findByProductLine(String productLines);
}
