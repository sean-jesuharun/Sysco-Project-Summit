package org.sps.order_microservice.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;

import java.util.List;

@Data
public class OrderUpdateReqDTO {

    @Valid
    private List<OrderItemReqDTO> items;

    @NotNull(message = "TotalPrice is Mandatory")
    @PositiveOrZero(message = "TotalPrice cannot be Negative")
    private Double totalPrice;

    @Valid
    private AddressReqDTO billingAddress;

}
