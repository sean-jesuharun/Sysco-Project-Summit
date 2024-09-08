package org.sps.cart_microservice.service;

import org.sps.cart_microservice.dto.request.CartItemReqDTO;
import org.sps.cart_microservice.dto.request.CartItemUpdateReqDTO;
import org.sps.cart_microservice.dto.request.CartReqDTO;
import org.sps.cart_microservice.dto.response.CartResDTO;

import java.util.List;

public interface CartService {

    List<CartResDTO> getCarts();

    CartResDTO createCart(CartReqDTO cartReqDTO);

    CartResDTO addItemToCart(Long cardId, CartItemReqDTO cartItemReqDTO);

    CartResDTO updateCartItem(Long cardId, Long itemId, CartItemUpdateReqDTO cartItemUpdateReqDTO);

    CartResDTO removeItemFromCart(Long cardId, Long itemId);

}
