package org.sps.cart_microservice.dto.response;

import lombok.Data;

@Data
public class CartItemResDTO {

    private Long productId;
    private Integer quantity;
    private Double unitPrice;
    private Double subtotal;

}
