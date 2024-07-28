package com.scaler.productservice.service;
import com.scaler.productservice.DTO.ProductDTO;
import com.scaler.productservice.model.Product;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;

public interface ProductService {

    public List<ProductDTO> getAllProducts();
    public ProductDTO getSingleProduct(Long id );
    public Product addNewProduct(Product product);
    public Product updateProduct(Long id,Product product);
    public void deleteProduct(Long id);








}
