package org.sps.cart_microservice.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;


@Data
public class CartReqDTO {

    @NotNull(message = "UserId is Mandatory")
    private Long userId;

}
