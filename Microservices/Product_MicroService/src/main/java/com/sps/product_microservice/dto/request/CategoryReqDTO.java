package com.sps.product_microservice.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CategoryReqDTO {

    @NotBlank(message = "CategoryName Is Mandatory")
    private String categoryName;;

}
