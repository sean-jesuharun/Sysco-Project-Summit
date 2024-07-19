package org.sps.order_microservice.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;
import lombok.ToString;

import java.util.Date;
import java.util.List;

@Data
@ToString
public class OrderReqDTO {

    @NotNull(message = "UserId is Mandatory")
    private Long userId;

    @NotNull(message = "OrderDate is Mandatory")
    @PastOrPresent(message = "OrderDate cannot be in Future")
    private Date orderDate;

    @Valid
    private List<OrderItemReqDTO> items;

    @NotNull(message = "TotalPrice is Mandatory")
    @PositiveOrZero(message = "TotalPrice cannot be Negative")
    private Double totalPrice;

    @Valid
    private AddressReqDTO billingAddress;

}
