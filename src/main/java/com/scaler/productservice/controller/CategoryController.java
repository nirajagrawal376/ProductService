package com.scaler.productservice.controller;
import com.scaler.productservice.DTO.CategoryDTO;
import com.scaler.productservice.model.Category;
import com.scaler.productservice.service.CategoryService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    private CategoryService categoryService;

    CategoryController(@Qualifier("CategoryServiceImpl") CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/{name}")
    public CategoryDTO getCategory(@PathVariable String name){
        return categoryService.getCategory(name);

    }

    @GetMapping()
    public List<CategoryDTO> getAllCategories() {
        return categoryService.getAllCategories();
    }
}
