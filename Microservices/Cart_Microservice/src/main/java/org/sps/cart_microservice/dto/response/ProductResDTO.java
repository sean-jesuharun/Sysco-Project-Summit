package org.sps.cart_microservice.dto.response;


import lombok.Data;
import lombok.ToString;

import java.util.Date;

@Data
@ToString
public class ProductResDTO {

    private Long id;

    private Long categoryId;

    private String productName;

    private Double price;

    private Date manufactureDate;

    private Date expiryDate;

}
