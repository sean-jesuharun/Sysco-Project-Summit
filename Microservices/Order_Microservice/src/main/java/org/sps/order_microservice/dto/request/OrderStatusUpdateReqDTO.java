package org.sps.order_microservice.dto.request;

import lombok.Data;
import org.sps.order_microservice.utils.OrderStatus;

@Data
public class OrderStatusUpdateReqDTO {

    private OrderStatus orderStatus;

}
