package org.sps.cart_microservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.sps.cart_microservice.entity.CartItem;
import org.sps.cart_microservice.entity.CartItemKey;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, CartItemKey> {

    CartItem findByCartItemKey(CartItemKey cartItemKey);

}
