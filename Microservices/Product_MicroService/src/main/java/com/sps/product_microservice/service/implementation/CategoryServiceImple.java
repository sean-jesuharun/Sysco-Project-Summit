package com.sps.product_microservice.service.implementation;

import com.sps.product_microservice.dto.request.CategoryReqDTO;
import com.sps.product_microservice.dto.response.CategoryResDTO;
import com.sps.product_microservice.entity.Category;
import com.sps.product_microservice.repository.CategoryRepository;
import com.sps.product_microservice.service.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImple implements CategoryService {

    private CategoryRepository categoryRepository;
    private ModelMapper modelMapper;

    @Autowired
    public CategoryServiceImple(CategoryRepository categoryRepository, ModelMapper modelMapper) {
        this.categoryRepository = categoryRepository;
        this.modelMapper = modelMapper;
    }

    // Retrieve all Categories
    public List<CategoryResDTO> getCategories(){

        // Fetching All the Categories from the Db
        // Mapping Category to CategoryDTO.
        // Converting the results to a list.
        return categoryRepository.findAll().stream()
                .map(category -> modelMapper.map(category, CategoryResDTO.class))
                .toList();

    }

    // Create a new Category
    public CategoryResDTO createCategory(CategoryReqDTO categoryReqDTO) {

        // Building a Category Transient Entity from CategoryDTO Data
        Category category = Category.builder()
                .categoryName(categoryReqDTO.getCategoryName())
                .build();

        // Saving new Category.
        categoryRepository.save(category);

        // Returning the saved category as a DTO
        return modelMapper.map(category, CategoryResDTO.class);

    }
}
