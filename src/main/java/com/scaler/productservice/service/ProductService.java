package com.scaler.productservice.service;
import com.scaler.productservice.DTO.ProductDTO;
import com.scaler.productservice.model.Product;

import java.util.List;

public interface ProductService {

    public List<ProductDTO> getAllProducts(int pageNumber, int pageSize);
    public ProductDTO getSingleProduct(Long id );
    public Product addNewProduct(Product product);
    public Product updateProduct(Long id,Product product);
    public void deleteProduct(Long id);


    ProductDTO getProductBasedOnUserRole(Long productId, Long userId);
}
