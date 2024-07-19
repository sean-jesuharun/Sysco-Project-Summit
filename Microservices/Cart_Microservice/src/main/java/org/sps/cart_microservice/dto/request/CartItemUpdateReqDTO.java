package org.sps.cart_microservice.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class CartItemUpdateReqDTO {

    @NotNull(message = "Quantity is Mandatory")
    @Positive(message = "Quantity cannot be Zero/Negative")
    private Integer quantity;

}
