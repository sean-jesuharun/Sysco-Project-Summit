package org.sps.order_microservice.service.implementation;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.sps.order_microservice.dto.request.OrderReqDTO;
import org.sps.order_microservice.dto.request.OrderStatusUpdateReqDTO;
import org.sps.order_microservice.dto.request.OrderUpdateReqDTO;
import org.sps.order_microservice.dto.response.OrderResDTO;
import org.sps.order_microservice.entity.Address;
import org.sps.order_microservice.entity.Order;
import org.sps.order_microservice.repository.OrderRepository;
import org.sps.order_microservice.service.OrderService;
import org.sps.order_microservice.utils.OrderStatus;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class OrderServiceImple implements OrderService {

    private OrderItemServiceImple orderItemServiceImple;

    private OrderRepository orderRepository;

    private EntityManager entityManager;

    private ModelMapper modelMapper;

    @Autowired
    public OrderServiceImple(OrderItemServiceImple orderItemServiceImple, OrderRepository orderRepository, EntityManager entityManager, ModelMapper modelMapper) {
        this.orderItemServiceImple = orderItemServiceImple;
        this.orderRepository = orderRepository;
        this.entityManager = entityManager;
        this.modelMapper = modelMapper;
    }

    // Get Orders
    public List<OrderResDTO> getOrders(Optional<Long> userId, Optional<OrderStatus> orderStatus) {

        // Fetching Orders from the Db based on filters.
        // Mapping Product to OrderResDTO.
        // Converting the results to a list.
        return orderRepository.findOrders(userId, orderStatus).stream()
                .map(order -> modelMapper.map(order, OrderResDTO.class))
                .toList();
    }

    // Create Order
    @Transactional
    public OrderResDTO createOrder(OrderReqDTO orderReqDTO) {

        // Building a Order Transient Entity from OrderReqDTO
        Order order = Order.builder()
                .userId(orderReqDTO.getUserId())
                .orderDate(orderReqDTO.getOrderDate())
                .totalPrice(orderReqDTO.getTotalPrice())
                .billingAddress(modelMapper.map(orderReqDTO.getBillingAddress(), Address.class))
                .orderStatus(OrderStatus.PENDING)
                .build();

        // Saving the Order
        orderRepository.save(order);

        // Creating OrderItems
        orderItemServiceImple.createOrderItems(order, orderReqDTO.getItems());

        // Flushing (Order and OrderItems) before refreshing Order.
        entityManager.flush();

        // Refreshing the Order to get the latest data (One with updated collections)
        entityManager.refresh(order);

        // Returning the Order as a OrderResDTO
        return modelMapper.map(order, OrderResDTO.class);

    }

    // Get a specific/particular Order using its id
    public OrderResDTO getOrderById(Long orderId) {

        // If the orderId available return the order or else Throw Exception
        return orderRepository.findById(orderId)
                .map(order -> modelMapper.map(order, OrderResDTO.class))
                .orElseThrow(() -> new NoSuchElementException("Order Not Found with Id : " + orderId));

    }

    // Update an existing Order using its id
    @Transactional
    public OrderResDTO updateOrderById(Long orderId, OrderUpdateReqDTO orderUpdateReqDTO) {

        // If the orderId available return the order or else Throw Exception
        return orderRepository.findById(orderId)
                .map(order -> {

                    // Delete Existing orderItems
                    orderItemServiceImple.deleteOrderItems(order.getItems());

                    // Creating New OrderItems
                    orderItemServiceImple.createOrderItems(order, orderUpdateReqDTO.getItems());

                    // Updating TotalPrice
                    order.setTotalPrice(orderUpdateReqDTO.getTotalPrice());
                    // Updating BillingAddress
                    order.setBillingAddress(modelMapper.map(orderUpdateReqDTO.getBillingAddress(), Address.class));

                    // Flushing (Order and OrderItems) before refreshing Order.
                    entityManager.flush();

                    // Refreshing the Order to get the latest data (One with updated collections)
                    entityManager.refresh(order);

                    // Returning the Order as a OrderResDTO
                    return modelMapper.map(order, OrderResDTO.class);

                })
                .orElseThrow(() -> new NoSuchElementException("Order Not Found with Id : " + orderId));

    }

    // Delete an Existing Order
    public void deleteOrderById(Long orderId) {

        orderRepository.delete(orderRepository.findById(orderId)
                .orElseThrow(() -> new NoSuchElementException("Order Not Found with Id : " + orderId)));

    }

    // Update OrderStatus for a specific Order using its id
    @Transactional
    public OrderResDTO updateOrderStatusById(Long orderId, OrderStatusUpdateReqDTO orderStatusUpdateReqDTO) {

        // If the orderId available return the order or else Throw Exception
        return orderRepository.findById(orderId)
                .map(order -> {

                    // Update the OrderStatus
                    order.setOrderStatus(orderStatusUpdateReqDTO.getOrderStatus());

                    // Returning the Order as a OrderResDTO
                    return modelMapper.map(order, OrderResDTO.class);

                })
                .orElseThrow(() -> new NoSuchElementException("Order Not Found with Id : " + orderId));
    }
}
