package org.sps.order_microservice.service;

import org.sps.order_microservice.dto.request.OrderReqDTO;
import org.sps.order_microservice.dto.request.OrderStatusUpdateReqDTO;
import org.sps.order_microservice.dto.request.OrderUpdateReqDTO;
import org.sps.order_microservice.dto.response.OrderResDTO;
import org.sps.order_microservice.utils.OrderStatus;

import java.util.List;
import java.util.Optional;

public interface OrderService {

    List<OrderResDTO> getOrders(Optional<Long> userId, Optional<OrderStatus> orderStatus);

    OrderResDTO createOrder(OrderReqDTO orderReqDTO);

    OrderResDTO getOrderById(Long orderId);

    OrderResDTO updateOrderById(Long orderId, OrderUpdateReqDTO orderUpdateReqDTO);

    void deleteOrderById(Long orderId);

    OrderResDTO updateOrderStatusById(Long orderId, OrderStatusUpdateReqDTO orderStatusUpdateReqDTO);


}
