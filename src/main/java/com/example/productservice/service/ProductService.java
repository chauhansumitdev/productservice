package com.example.productservice.service;

import com.example.productservice.dto.ResponseAuth;
import com.example.productservice.entity.Product;
import com.example.productservice.repository.ProductRepository;
import com.example.productservice.retrofit.AuthResolver;
import org.hibernate.query.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final AuthResolver authResolver;

    @Autowired
    public ProductService(AuthResolver authResolver,ProductRepository productRepository){
        this.productRepository = productRepository;
        this.authResolver = authResolver;
    }



    public Product createProduct(Product product, String token) throws Exception{

        ResponseAuth responseAuth = authResolver.verify(token.substring(7));

        String role = responseAuth.getRole();
        
        if(responseAuth.getRole().equals("ADMIN")){
            return productRepository.save(product);
        }

        throw new Exception("ONLY ADMIN CAN ADD PRODUCTS");
    }


    public Optional<Product> getProductById(UUID id) {
        return productRepository.findById(id);
    }

    public Product updateProduct(UUID id, Product updatedProduct, String token) throws Exception{

        ResponseAuth responseAuth = authResolver.verify(token.substring(7));

        if(responseAuth.getRole().equals("ADMIN")){

            Optional<Product> product = productRepository.findById(id);

            if(!product.isPresent()){
                throw new Exception("PRODUCT DOES NOT EXIST");
            }

            Product product1 = product.get();

            product1.setName(updatedProduct.getName());
            product1.setPrice(updatedProduct.getPrice());

            return productRepository.save(product1);

        }

        throw new Exception("ONLY ADMIN CAN UPDATE PRODUCT");

    }


    public Page<Product> getAllProducts(int page, int size){
        return productRepository.findAll(PageRequest.of(page, size));
    }
}