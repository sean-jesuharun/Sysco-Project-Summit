package org.sps.order_microservice.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class AddressReqDTO {

    @NotBlank(message = "Street is Mandatory")
    private String street;

    @NotBlank(message = "City is Mandatory")
    private String city;

    @NotBlank(message = "PostCode is Mandatory")
    private String postCode;

    @NotBlank(message = "Country is Mandatory")
    private String country;

}
