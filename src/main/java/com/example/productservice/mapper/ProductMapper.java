package com.example.productservice.mapper;

import com.example.productservice.dto.ProductDTO;
import com.example.productservice.entity.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {

    public ProductDTO toDTO(Product product) {
        return new ProductDTO(
                product.getId(),
                product.getName(),
                product.getPrice(),
                product.getCreatedAt(),
                product.getUpdatedAt()
        );
    }

    public Product toEntity(ProductDTO productDTO) {
        return new Product(
                productDTO.getId(),
                productDTO.getName(),
                productDTO.getPrice(),
                productDTO.getCreatedAt(),
                productDTO.getUpdatedAt()
        );
    }
}
