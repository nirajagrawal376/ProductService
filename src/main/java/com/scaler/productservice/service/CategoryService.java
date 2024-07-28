package com.scaler.productservice.service;

import com.scaler.productservice.DTO.CategoryDTO;
import com.scaler.productservice.model.Category;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public interface CategoryService {

    public CategoryDTO getCategory(String Name);

    public List<CategoryDTO> getAllCategories();

}
