package com.example.cbm.dtos;
import com.example.cbm.entities.OrderDetails;
import com.example.cbm.entities.Products;
import lombok.*;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class OrderDetailsProductsResponse {
    private OrderDetails orderDetails;
    private List<Products> products;
}
