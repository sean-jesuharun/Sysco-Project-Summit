package com.sps.product_microservice.dto.response;


import lombok.Data;

import java.util.Date;

@Data
public class ProductResDTO {

    private Long id;

    private Long categoryId;

    private String productName;

    private Double price;

    private Date manufactureDate;

    private Date expiryDate;

}
