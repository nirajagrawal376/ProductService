package com.scaler.productservice.serviceImpl;

import com.scaler.productservice.DTO.CategoryDTO;
import com.scaler.productservice.model.Category;
import com.scaler.productservice.service.CategoryService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@Qualifier("FakeStoreCategoryService")
public class FakeStoreCategoryService implements CategoryService {
    @Override
    public CategoryDTO getCategory(String Name) {
        return null;
    }

    @Override
    public ArrayList<CategoryDTO> getAllCategories() {
        return null;
    }
}
