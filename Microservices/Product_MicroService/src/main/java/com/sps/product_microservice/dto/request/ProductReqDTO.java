package com.sps.product_microservice.dto.request;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.util.Date;

@Data
public class ProductReqDTO {

    @NotNull(message = "CategoryId Is Mandatory")
    private Long categoryId;

    @NotBlank(message = "ProductName Is Mandatory")
    private String productName;

    @NotNull(message = "Price Is Mandatory")
    @PositiveOrZero
    private Double price;

    @NotNull(message = "ManufactureDate Is Mandatory")
    @PastOrPresent(message = "ManufactureDate Must be in Past")
    private Date manufactureDate;

    @NotNull(message = "ExpiryDate Is Mandatory")
    @FutureOrPresent(message = "ExpiryDate Must be in Future")
    private Date expiryDate;

}
