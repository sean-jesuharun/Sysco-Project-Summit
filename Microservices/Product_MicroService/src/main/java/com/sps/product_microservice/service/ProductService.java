package com.sps.product_microservice.service;

import com.sps.product_microservice.dto.request.ProductReqDTO;
import com.sps.product_microservice.dto.response.ProductResDTO;
import com.sps.product_microservice.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

import java.util.Optional;

public interface ProductService {

    // Retrieve Products
    Page<ProductResDTO> getProducts(Optional<Long> categoryId, Pageable pageable);

    // Create a new Product
    ProductResDTO createProduct(ProductReqDTO productReqDTO);

    // Retrieve a specific/particular product using its id
    ProductResDTO getProductById(Long productId);

    // Update an Existing product
    ProductResDTO updateProductById(Long productId, ProductReqDTO productReqDTO);

    // Delete an Existing product
    void deleteProductById(Long productId);

}
