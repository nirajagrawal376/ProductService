package com.scaler.productservice.serviceImpl;

import com.scaler.productservice.DTO.CategoryDTO;
import com.scaler.productservice.model.Category;
import com.scaler.productservice.repositories.CategoryRepository;
import com.scaler.productservice.service.CategoryService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Qualifier("CategoryServiceImpl")
public class CategoryServiceImpl implements CategoryService {

    CategoryRepository categoryRepository;

    CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }
    @Override
    public CategoryDTO getCategory(String name) {
        Optional<Category> categoryOpt = categoryRepository.findByName(name);
        CategoryDTO categoryDTO=null;
        if (categoryOpt.isPresent()) {
             categoryDTO = categoryToCategoryDTO(categoryOpt.get());
        }else{
            return null;
        }
        return categoryDTO;
    }

    @Override
    public List<CategoryDTO> getAllCategories() {

         List<Category> categoryList = categoryRepository.findAll();
         List<CategoryDTO> categoryDTOList = new ArrayList<>();
         for(Category category : categoryList){
             categoryDTOList.add(categoryToCategoryDTO(category));
         }
            return categoryDTOList;
    }

    public CategoryDTO categoryToCategoryDTO(Category category) {
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setName(category.getName());
        categoryDTO.setDescription(category.getDescription());
        return categoryDTO;
    }

    public Category categoryDTOToCategory(CategoryDTO categoryDTO) {
        Category category = new Category();
        category.setName(categoryDTO.getName());
        category.setDescription(categoryDTO.getDescription());
        return category;
    }
}
