package org.sps.order_microservice.dto.response;

import lombok.Data;

@Data
public class OrderItemResDTO {

    private Long productId;

    private Integer quantity;

    private Double price;

}
