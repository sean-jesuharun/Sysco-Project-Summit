package org.sps.order_microservice.dto.response;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class OrderResDTO {

    private Long id;

    private Long userId;

    private Date orderDate;

    private List<OrderItemResDTO> items;

    private Double totalPrice;

    private AddressResDTO billingAddress;

    private String orderStatus;

}
