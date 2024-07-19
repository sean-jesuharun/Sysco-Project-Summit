package com.sps.product_microservice.controller;

import com.sps.product_microservice.dto.request.ProductReqDTO;
import com.sps.product_microservice.service.implementation.ProductServiceImple;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RestController
@RequestMapping("products")
@CrossOrigin
public class ProductController extends AbstractController{

    private ProductServiceImple productServiceImple;

    @Autowired
    public ProductController(ProductServiceImple productServiceImple) {
        this.productServiceImple = productServiceImple;
    }

    @GetMapping
    public ResponseEntity<Object> getProducts(
            @RequestParam(name = "pageNo", defaultValue = "0") int pageNo,
            @RequestParam(name = "pageSize", defaultValue = "2") int pageSize,
            @RequestParam(name = "categoryId") Optional<Long> categoryId) {
        return handleSuccessfulOkResponse(productServiceImple.getProducts(categoryId, PageRequest.of(pageNo, pageSize)));
    }

    @PostMapping
    public ResponseEntity<Object> createProduct(@Valid @RequestBody ProductReqDTO productReqDTO){
        return handleSuccessfulCreatedResponse(productServiceImple.createProduct(productReqDTO));
    }

    @GetMapping("{productId}")
    public ResponseEntity<Object> getProductById(@PathVariable(name = "productId") Long productId){
        return handleSuccessfulOkResponse(productServiceImple.getProductById(productId));
    }

    @PutMapping("{productId}")
    public ResponseEntity<Object> updateProductById(@PathVariable(name = "productId") Long productId, @Valid @RequestBody ProductReqDTO productReqDTO){
        return handleSuccessfulOkResponse(productServiceImple.updateProductById(productId, productReqDTO));
    }

    @DeleteMapping("{productId}")
    public ResponseEntity<HttpStatus> deleteProductById(@PathVariable(name = "productId") Long productId){
        productServiceImple.deleteProductById(productId);
        return handleSuccessfulNoContentResponse();
    }

}
