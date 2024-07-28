package com.scaler.productservice.model;

import jakarta.persistence.*;
import lombok.Data;
@Entity
@Data
public class Product extends BaseModel{

    private String title;
    @ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    private Category category;
    private Double price;
    private String description;
    private String imageUrl;
}
