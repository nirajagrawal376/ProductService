package com.scaler.productservice.controller;
import com.scaler.productservice.DTO.ProductDTO;
import com.scaler.productservice.commons.AuthenticationCommons;
import com.scaler.productservice.model.Category;
import com.scaler.productservice.model.Product;
import com.scaler.productservice.service.ProductService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Qualifier("productServiceImpl")
    ProductService productService;

    private AuthenticationCommons authenticationCommons;

    ProductController(@Qualifier("productServiceImpl") ProductService productService, AuthenticationCommons authenticationCommons) {
        this.productService = productService;
        this.authenticationCommons = authenticationCommons;;
    }

    @GetMapping()
    public List<ProductDTO> getAllProducts(@RequestHeader("AuthnticationToken") String token){
        authenticationCommons.ValidateToken(token);
        return productService.getAllProducts();
    }

    @GetMapping("/{id}")
    public ProductDTO getSingleProduct(@PathVariable("id") long id) {
        return productService.getSingleProduct(id);
    }

    @PostMapping()
    public Product addNewProduct(@RequestBody Product product){
        return productService.addNewProduct(product);
    }

    @PutMapping("/{id}")
    public Product updateProduct(@PathVariable(value = "id") Long id, @RequestBody Product product){
        return productService.updateProduct(id, product);
    }
    @DeleteMapping("/{id}")
    public boolean  deleteProduct(@PathVariable Long id){
        productService.deleteProduct(id);
        return true;
    }

    @PostMapping("/validate")
    public void validateToken(String token){

    }
}

