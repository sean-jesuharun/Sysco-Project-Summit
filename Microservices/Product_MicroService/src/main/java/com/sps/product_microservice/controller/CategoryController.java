package com.sps.product_microservice.controller;

import com.sps.product_microservice.dto.request.CategoryReqDTO;
import com.sps.product_microservice.service.implementation.CategoryServiceImple;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("categories")
@CrossOrigin
public class CategoryController extends AbstractController{

    private CategoryServiceImple categoryServiceImple;

    @Autowired
    public CategoryController(CategoryServiceImple categoryServiceImple) {
        this.categoryServiceImple = categoryServiceImple;
    }

    @GetMapping
    public ResponseEntity<Object> getCategories(){
        return handleSuccessfulOkResponse(categoryServiceImple.getCategories());
    }

    @PostMapping
    public ResponseEntity<Object> createCategory(@Valid @RequestBody CategoryReqDTO categoryReqDTO){
        return handleSuccessfulCreatedResponse(categoryServiceImple.createCategory(categoryReqDTO));
    }

}
