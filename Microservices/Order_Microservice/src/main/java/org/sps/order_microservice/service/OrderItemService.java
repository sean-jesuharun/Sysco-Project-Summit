package org.sps.order_microservice.service;

import org.sps.order_microservice.dto.request.OrderItemReqDTO;
import org.sps.order_microservice.dto.response.OrderItemResDTO;
import org.sps.order_microservice.entity.Order;
import org.sps.order_microservice.entity.OrderItem;

import java.util.List;

public interface OrderItemService {

    List<OrderItemResDTO> createOrderItems(Order order, List<OrderItemReqDTO> items);

    void deleteOrderItems(List<OrderItem> orderItems);
}
