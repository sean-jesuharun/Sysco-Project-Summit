package org.sps.cart_microservice.dto.response;

import lombok.Data;

import java.time.OffsetDateTime;
import java.util.List;

@Data
public class CartResDTO {

    private Long id;
    private Long userId;
    private OffsetDateTime creationTime;
    private List<CartItemResDTO> items;
    private Double totalPrice;
    private OffsetDateTime lastUpdateTime;

}
