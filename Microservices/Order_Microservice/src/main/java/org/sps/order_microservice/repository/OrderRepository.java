package org.sps.order_microservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.sps.order_microservice.entity.Order;
import org.sps.order_microservice.utils.OrderStatus;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    // Dynamic Query to Filter Orders
    @Query("SELECT o FROM Order o WHERE "
            + "(:userId IS NULL OR o.userId = :userId) AND "
            + "(:orderStatus IS NULL OR o.orderStatus = :orderStatus)")
    List<Order> findOrders(@Param("userId") Optional<Long> userId, @Param("orderStatus") Optional<OrderStatus> orderStatus);


    @Query(value = "select o from Order o where o.id=:id")
    Order findByOrderId(@Param("id") Long id);

    List<Order> findByOrderStatus(String status);

}
