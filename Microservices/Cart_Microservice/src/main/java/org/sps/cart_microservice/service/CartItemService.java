package org.sps.cart_microservice.service;

import org.sps.cart_microservice.dto.request.CartItemReqDTO;
import org.sps.cart_microservice.dto.request.CartItemUpdateReqDTO;
import org.sps.cart_microservice.dto.response.CartItemResDTO;
import org.sps.cart_microservice.entity.Cart;
import org.sps.cart_microservice.entity.CartItem;
import org.sps.cart_microservice.entity.CartItemKey;

public interface CartItemService {

    CartItem createCartItem(Cart cart, CartItemReqDTO cartItemReqDTO);

    void updateCartItem(CartItemKey cartItemKey, CartItemUpdateReqDTO cartItemUpdateReqDTO);

    CartItem deleteCartItem(CartItemKey cartItemKey);

}
