package org.sps.cart_microservice.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "cart_item_t")
@DynamicUpdate
@ToString
public class CartItem {

    @EmbeddedId
    private CartItemKey cartItemKey;

    @ManyToOne
    @JoinColumn(name = "cart_id", insertable = false, updatable = false)
    private Cart cart;

    @Basic
    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    @Basic
    @Column(name = "unit_price", nullable = false)
    private Double unitPrice;

    @Basic
    @Column(name = "subtotal", nullable = false)
    private Double subtotal;

}
