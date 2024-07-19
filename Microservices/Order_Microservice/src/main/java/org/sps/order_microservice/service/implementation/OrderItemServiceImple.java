package org.sps.order_microservice.service.implementation;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.sps.order_microservice.dto.request.OrderItemReqDTO;
import org.sps.order_microservice.dto.response.OrderItemResDTO;
import org.sps.order_microservice.entity.Order;
import org.sps.order_microservice.entity.OrderItem;
import org.sps.order_microservice.entity.OrderItemKey;
import org.sps.order_microservice.repository.OrderItemRepository;
import org.sps.order_microservice.service.OrderItemService;

import java.util.List;

@Service
public class OrderItemServiceImple implements OrderItemService {

    private final OrderItemRepository orderItemRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public OrderItemServiceImple(OrderItemRepository orderItemRepository, ModelMapper modelMapper) {
        this.orderItemRepository = orderItemRepository;
        this.modelMapper = modelMapper;
    }

    // Create OrderItems.
    public List<OrderItemResDTO> createOrderItems(Order order, List<OrderItemReqDTO> orderItemReqDTOs) {

        return orderItemReqDTOs.stream()
                .map(orderItemReqDTO -> {

                    OrderItem orderItem = OrderItem.builder()
                    .orderItemKey(OrderItemKey.builder()
                            .productId(orderItemReqDTO.getProductId())
                            .order(order)
                            .build())
                    .quantity(orderItemReqDTO.getQuantity())
                    .price(orderItemReqDTO.getPrice())
                    .build();

                    // Saving OrderItem
                    orderItemRepository.save(orderItem);

                    // Mapping OrderItem to OrderItemResDTO
                    return modelMapper.map(orderItem, OrderItemResDTO.class);

                })
                .toList();

    }

    // Delete OrderItems
    public void deleteOrderItems(List<OrderItem> orderItems) {

        orderItemRepository.deleteAll(orderItems);

    }

}
