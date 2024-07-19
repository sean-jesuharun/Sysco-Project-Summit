package org.sps.order_microservice.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class OrderItemReqDTO {

    @NotNull(message = "ProductId is Mandatory")
    private Long productId;

    @NotNull(message = "Quantity is Mandatory")
    @Positive(message = "Quantity cannot be Zero/Negative")
    private Integer quantity;

    @NotNull(message = "Price is Mandatory")
    @PositiveOrZero(message = "Price cannot be Negative")
    private Double price;

}
