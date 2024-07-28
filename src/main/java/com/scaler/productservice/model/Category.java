package com.scaler.productservice.model;

import jakarta.persistence.*;
import lombok.Data;
import java.util.List;
@Entity
@Data
public class Category extends BaseModel{

    private String name;
    private String description;
    @OneToMany(mappedBy = "category")
    private List<Product> products ;
 }
