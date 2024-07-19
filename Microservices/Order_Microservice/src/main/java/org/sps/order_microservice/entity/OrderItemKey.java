package org.sps.order_microservice.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Embeddable
@ToString
public class OrderItemKey {

    @Basic
    @Column(name = "product_id", nullable = false)
    private Long productId;

    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

}
