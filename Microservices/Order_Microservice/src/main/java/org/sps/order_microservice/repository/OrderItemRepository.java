package org.sps.order_microservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.sps.order_microservice.entity.OrderItem;
import org.sps.order_microservice.entity.OrderItemKey;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, OrderItemKey> {

    void deleteByOrderId(long orderId);

}
