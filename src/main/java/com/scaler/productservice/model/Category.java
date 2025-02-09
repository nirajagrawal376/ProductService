package com.scaler.productservice.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Entity
@Data
@Getter
@Setter
public class Category extends BaseModel{

	private String name;
    private String description;
    public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public List<Product> getProducts() {
		return products;
	}
	public void setProducts(List<Product> products) {
		this.products = products;
	}
	@OneToMany(mappedBy = "category")
    private List<Product> products ;
	
 }
