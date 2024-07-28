package com.scaler.productservice.DTO;

import lombok.Data;

import java.util.List;

@Data
public class CategoryDTO {

    private String name;
     private String description;
     private List<ProductDTO> productList;
}
