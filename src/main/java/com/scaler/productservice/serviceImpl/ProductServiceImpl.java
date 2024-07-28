package com.scaler.productservice.serviceImpl;

import com.scaler.productservice.DTO.ProductDTO;
import com.scaler.productservice.model.Category;
import com.scaler.productservice.model.Product;
import com.scaler.productservice.repositories.CategoryRepository;
import com.scaler.productservice.repositories.ProductRepository;
import com.scaler.productservice.service.CategoryService;
import com.scaler.productservice.service.ProductService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Qualifier("productServiceImpl")
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    ProductServiceImpl(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<ProductDTO> getAllProducts() {
        System.out.println("Fetching all products");
        List<Product> products =  productRepository.findAll();
        List<ProductDTO> productDTOS = new ArrayList<>();
        for(Product product : products ){
            ProductDTO productDTO = convertProductToProductDTO(product);
            productDTOS.add(productDTO);
        }
        return productDTOS;
    }

    @Override
    public ProductDTO getSingleProduct(Long id) {
        System.out.println("Fetching single product");
        Optional<Product> optionalProduct=productRepository.findById(Math.toIntExact(id));
        if(optionalProduct.isEmpty()) {
            throw new RuntimeException("Product is not present ");
        }
        Product product = optionalProduct.get();
        return convertProductToProductDTO(product);
    }

    //findById() retrieves the product data itself (title, ID, etc.).
    //However, it doesn't automatically fetch the category data by default with FetchType.LAZY

    //If you try to convert the retrieved product object to JSON (e.g., in an API response) after findById(),
    // the serializer encounters the proxy object for the category attribute. Since it doesn't have the actual category data,
    // the conversion fails.

    @Override
    public Product addNewProduct(Product product) { // the product that we are passing won't contain ID
        //1. Add a new product with an existing category ( No Product id but with category id )
        //2. Add a new product with a new category ( No product id and no category id )
        Category category = product.getCategory();
        Optional<Category> categoryOpt = categoryRepository.findByName(category.getName());

        if(categoryOpt.isPresent()) {
            product.setCategory(categoryOpt.get());
        }else{
            throw new RuntimeException("Category is not present");
        }
        return productRepository.save(product);
    }

    @Override
    public Product updateProduct(Long id, Product product) {// the product that we are passing will contain ID
        Optional<Product> optProduct = productRepository.findById(Math.toIntExact(id));
        if(optProduct.isEmpty()){
            throw new RuntimeException("Product is not present ");
        }
        Product product1 = optProduct.get();
        if(product.getPrice()!=null){
            product1.setPrice(product.getPrice());
        }if(product.getCategory()!=null){
            product1.setCategory(product.getCategory());
        }if(product.getTitle()!=null){
            product1.setTitle(product.getTitle());
        }if(product.getDescription()!=null){
            product1.setDescription(product.getDescription());
        }if(product.getImageUrl()!=null){
            product1.setImageUrl(product.getImageUrl());
        }

        return productRepository.save(product1);
    }

    @Override
    public void deleteProduct(Long id) {
          productRepository.deleteById(Math.toIntExact(id));
    }


    public Product convertProductDTOtoProduct(ProductDTO productDTO){
        Product product = new Product();
        product.setId(productDTO.getId());
        product.setDescription(productDTO.getDescription());
        product.setPrice(productDTO.getPrice());
        product.setTitle(productDTO.getTitle());
        product.setImageUrl(productDTO.getImage());
        Category category = new Category();
        category.setName(productDTO.getCategory());
        product.setCategory(category);
        return product;
    }

    public ProductDTO convertProductToProductDTO(Product product ) {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setCategory(product.getCategory().getName());
        productDTO.setTitle(product.getTitle());
        productDTO.setPrice(product.getPrice());
        productDTO.setId(product.getId());
        productDTO.setDescription(product.getDescription());
        productDTO.setImage(product.getImageUrl());
        return productDTO;
    }
}
