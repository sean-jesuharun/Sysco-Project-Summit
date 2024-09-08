package org.sps.order_microservice.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.sps.order_microservice.dto.request.OrderReqDTO;
import org.sps.order_microservice.dto.request.OrderStatusUpdateReqDTO;
import org.sps.order_microservice.dto.request.OrderUpdateReqDTO;
import org.sps.order_microservice.service.implementation.OrderServiceImple;
import org.sps.order_microservice.utils.OrderStatus;

import java.util.Optional;


@RestController
@RequestMapping("orders")
@CrossOrigin
public class OrderController extends AbstractController {

    private OrderServiceImple orderServiceImple;

    @Autowired
    public OrderController(OrderServiceImple orderServiceImple) {
        this.orderServiceImple = orderServiceImple;
    }

    @GetMapping
    public ResponseEntity<Object> getOrders(@RequestParam("userId") Optional<Long> userId, @RequestParam("orderStatus") Optional<OrderStatus> orderStatus){
        return handleSuccessfulOkResponse(orderServiceImple.getOrders(userId, orderStatus));
    }

    @PostMapping
    public ResponseEntity<Object> createOrder(@Valid @RequestBody OrderReqDTO orderReqDTO){
        return handleSuccessfulCreatedResponse(orderServiceImple.createOrder(orderReqDTO));
    }

    @GetMapping("{orderId}")
    public ResponseEntity<Object> getOrderById(@PathVariable("orderId") Long orderId){
        return handleSuccessfulOkResponse(orderServiceImple.getOrderById(orderId));
    }

    @PatchMapping("{orderId}")
    public ResponseEntity<Object> updateOrderById(@PathVariable("orderId") Long orderId, @Valid @RequestBody OrderUpdateReqDTO orderUpdateReqDTO){
        return handleSuccessfulOkResponse(orderServiceImple.updateOrderById(orderId, orderUpdateReqDTO));
    }

    @DeleteMapping("{orderId}")
    public ResponseEntity<HttpStatus> deleteOrderById(@PathVariable("orderId") Long orderId){
        orderServiceImple.deleteOrderById(orderId);
        return handleSuccessfulNoContentResponse();
    }

//    @PatchMapping("{orderId}/orderStatus")
//    public ResponseEntity<Object> updateOrderStatusById(@PathVariable("orderId") Long orderId, @RequestBody OrderStatusUpdateReqDTO orderStatusUpdateReqDTO){
//        return handleSuccessfulOkResponse(orderServiceImple.updateOrderStatusById(orderId, orderStatusUpdateReqDTO));
//    }

}
