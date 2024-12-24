package com.product.productService.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Document(collection = "product")
public class product {
    @Id
    private String id;
    private String name;
    private String Skucode;
    private String description;
    private String price;
    private String category;

   

  
}
