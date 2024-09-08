package com.sps.product_microservice.service;

import com.sps.product_microservice.dto.request.CategoryReqDTO;
import com.sps.product_microservice.dto.response.CategoryResDTO;

import java.util.List;

public interface CategoryService {

    // Retrieve all Categories
    List<CategoryResDTO> getCategories();

    // Create a new Category
    CategoryResDTO createCategory(CategoryReqDTO categoryReqDTO);

}
