package com.sps.product_microservice.service.implementation;

import com.sps.product_microservice.dto.request.ProductReqDTO;
import com.sps.product_microservice.dto.response.ProductResDTO;
import com.sps.product_microservice.entity.Product;
import com.sps.product_microservice.repository.CategoryRepository;
import com.sps.product_microservice.repository.ProductRepository;
import com.sps.product_microservice.service.ProductService;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class ProductServiceImple implements ProductService {

    private ProductRepository productRepository;

    private CategoryRepository categoryRepository;

    private EntityManager entityManager;

    private ModelMapper modelMapper;

    @Autowired
    public ProductServiceImple(ProductRepository productRepository, CategoryRepository categoryRepository, EntityManager entityManager, ModelMapper modelMapper) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.entityManager = entityManager;
        this.modelMapper = modelMapper;
    }

    // Retrieve Products
    public Page<ProductResDTO> getProducts(Optional<Long> categoryId, Pageable pageable) {

        // Fetching Page<Product> from the Db Based on Filters and Pagination.
        // Map Product in Page<Product> to ProductResDTO using Page Map() to create Page<ProductResDTO>.
        return productRepository.findProducts(categoryId, pageable)
                .map(product -> modelMapper.map(product, ProductResDTO.class));

    }

    // Create a new Product
    public ProductResDTO createProduct(ProductReqDTO productReqDTO){

        // Building a Product Transient Entity from ProductReqDTO.
        Product product = Product.builder()
                .category(categoryRepository.findById(productReqDTO.getCategoryId())
                        .orElseThrow(() -> new NoSuchElementException("Category Not Found with Id : " + productReqDTO.getCategoryId())))
                .productName(productReqDTO.getProductName())
                .price(productReqDTO.getPrice())
                .manufactureDate(productReqDTO.getManufactureDate())
                .expiryDate(productReqDTO.getExpiryDate())
                .build();

        // Saving new Product.
        productRepository.save(product);

        // Issue in Date format sending Might be Think of flush and Refresh.

        // Returning the saved product as a ProductResDTO
        return modelMapper.map(product, ProductResDTO.class);

    }

    // Retrieve a specific/particular Product using its id.
    public ProductResDTO getProductById(Long productId) {

        // If the productId available return the product or else Throw Exception
        return productRepository.findById(productId)
                .map(product -> modelMapper.map(product, ProductResDTO.class))
                .orElseThrow(() -> new NoSuchElementException("Product Not Found with Id : " + productId));

    }

    // Update an Existing product using its id.
    @Transactional
    public ProductResDTO updateProductById(Long productId, ProductReqDTO productReqDTO) {

        // If the productId available return the product or else Throw Exception
         return productRepository.findById(productId)
                 .map(product -> {

                     // Updating all the fields using ProductReqDTO
                     product.setCategory(categoryRepository.findById(productReqDTO.getCategoryId())
                             .orElseThrow(() -> new NoSuchElementException("Category Not Found with Id : " + productReqDTO.getCategoryId())));
                     product.setProductName(productReqDTO.getProductName());
                     product.setPrice(productReqDTO.getPrice());
                     product.setManufactureDate(productReqDTO.getManufactureDate());
                     product.setExpiryDate(productReqDTO.getExpiryDate());

                     // Issue in Date format sending Might be Think of flush and Refresh.

                     // Returning product as a ProductResDTO
                     return modelMapper.map(product, ProductResDTO.class);

                 })
                .orElseThrow(() -> new NoSuchElementException("Product Not Found with Id : " + productId));

    }

    // Delete an Existing product
    public void deleteProductById(Long productId) {

        // If the Product exist Deleting it or else Throw Exception
        productRepository.delete(productRepository.findById(productId)
                .orElseThrow(() -> new NoSuchElementException("Product Not Found with Id : " + productId)));

    }
}
