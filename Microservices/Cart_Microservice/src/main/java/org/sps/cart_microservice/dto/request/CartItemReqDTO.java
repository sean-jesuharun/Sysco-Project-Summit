package org.sps.cart_microservice.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class CartItemReqDTO {

    @NotNull(message = "ProductId is Mandatory")
    private Long productId;

    @NotNull(message = "Quantity is Mandatory")
    @Positive(message = "Quantity cannot be Zero/Negative")
    private Integer quantity;

    @Null(message = "UnitPrice must be Null")
    private Double unitPrice;

}
