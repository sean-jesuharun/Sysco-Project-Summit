package org.sps.cart_microservice.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Embeddable
@ToString
public class CartItemKey {

    @Basic
    @Column(name = "product_id", nullable = false)
    private Long productId;

    @ManyToOne
    @JoinColumn(name = "cart_id", nullable = false)
    private Cart cart;

}
