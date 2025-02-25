package com.example.productservice.dto;


import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {

    private UUID id;

    @NotBlank
    private String name;

    @NotBlank
    private Double price;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

}
