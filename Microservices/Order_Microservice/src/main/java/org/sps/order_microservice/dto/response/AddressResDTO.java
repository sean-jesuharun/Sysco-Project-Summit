package org.sps.order_microservice.dto.response;

import lombok.Data;

@Data
public class AddressResDTO {

    private String street;
    private String city;
    private String postCode;
    private String country;

}
