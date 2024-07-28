package com.scaler.productservice.serviceImpl;
import com.scaler.productservice.DTO.ProductDTO;
import com.scaler.productservice.model.Category;
import com.scaler.productservice.model.Product;
import com.scaler.productservice.service.ProductService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpMessageConverterExtractor;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@Service
@Qualifier("fakeStoreProductService")
public class FakeStoreProductService implements ProductService {

    RestTemplate restTemplate;

    FakeStoreProductService(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }
    public Product convertProductDTOtoProduct(ProductDTO fakeStoreProductDTO){
        Product product = new Product();
        product.setId(fakeStoreProductDTO.getId());
        product.setDescription(fakeStoreProductDTO.getDescription());
        product.setPrice(fakeStoreProductDTO.getPrice());
        product.setTitle(fakeStoreProductDTO.getTitle());
        product.setImageUrl(fakeStoreProductDTO.getImage());
        Category category = new Category();
        category.setName(fakeStoreProductDTO.getCategory());
        product.setCategory(category);
        return product;
    }

    public ProductDTO convertProductToProductDTO(Product product ) {
        ProductDTO fakeStoreProductDTO = new ProductDTO();
        fakeStoreProductDTO.setCategory(product.getCategory().getName());
        fakeStoreProductDTO.setTitle(product.getTitle());
        fakeStoreProductDTO.setPrice(product.getPrice());
        fakeStoreProductDTO.setId(product.getId());
        fakeStoreProductDTO.setDescription(product.getDescription());
        fakeStoreProductDTO.setImage(product.getImageUrl());
        return fakeStoreProductDTO;
    }

    @Override
    public List<ProductDTO> getAllProducts() {
        ProductDTO[] productDTOs = restTemplate.getForObject(
                "https://fakestoreapi.com/products",
                ProductDTO[].class);
        return new ArrayList<>(Arrays.asList(productDTOs));
    }

    @Override
    public ProductDTO getSingleProduct(Long id ) {
        ProductDTO productDTO = restTemplate.getForObject(
                "https://fakestoreapi.com/products/"+1,
                ProductDTO.class
        ); // this method takes 2 arguments 1. url 2. the data type of data that we will get back ( DTO )
        return productDTO;
    }

    @Override
    public Product addNewProduct(Product product) {
        ProductDTO fakeStoreProductDTO = convertProductToProductDTO(product);
        ProductDTO fakeStoreProductDTO1= restTemplate.postForObject
                ("https://fakestoreapi.com/products",fakeStoreProductDTO,ProductDTO.class);
        return convertProductDTOtoProduct(fakeStoreProductDTO1);
    }

    @Override
    public Product updateProduct(Long id, Product product) {
        // we need to convert the product into   cause that is what we are passing to the FakeStore
        ProductDTO fakeStoreProductDTO = convertProductToProductDTO(product);
        RequestCallback requestCallback = restTemplate.httpEntityCallback(fakeStoreProductDTO, ProductDTO.class);
        // here the above method accepts request body ( that is the object of request  )as 1st parameter and The response type as the 2nd parameter
        // now the request body will be an object of class ProductDTO this object is basically what we have to update in the fake store
        // and the response type will be ProductDTO.class
        HttpMessageConverterExtractor<ProductDTO> responseExtractor =
                new HttpMessageConverterExtractor<>(ProductDTO.class, restTemplate.getMessageConverters());
         ProductDTO resultProductDTO =
                 restTemplate.execute("https://fakestoreapi.com/products/"+id, HttpMethod.PUT, requestCallback, responseExtractor);
         return convertProductDTOtoProduct(resultProductDTO);
    }

    @Override
    public void deleteProduct(Long id) {
        restTemplate.delete("https://fakestoreapi.com/products/"+id);
    }
}
