package com.scaler.productservice.DTO;

import lombok.Data;

import java.util.List;

@Data
public class CategoryDTO {

    private String name;
     private String description;
     private List<ProductDTO> productList;
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
	public List<ProductDTO> getProductList() {
		return productList;
	}
	public void setProductList(List<ProductDTO> productList) {
		this.productList = productList;
	}
}
