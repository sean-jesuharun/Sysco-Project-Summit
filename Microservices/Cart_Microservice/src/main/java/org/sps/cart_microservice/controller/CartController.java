package org.sps.cart_microservice.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.sps.cart_microservice.dto.request.CartItemReqDTO;
import org.sps.cart_microservice.dto.request.CartItemUpdateReqDTO;
import org.sps.cart_microservice.dto.request.CartReqDTO;
import org.sps.cart_microservice.service.implementation.CartServiceImple;

@RestController
@RequestMapping("carts")
@CrossOrigin
public class CartController extends AbstractController{

    private CartServiceImple cartServiceImple;

    @Autowired
    public CartController(CartServiceImple cartServiceImple) {
        this.cartServiceImple = cartServiceImple;
    }

    @GetMapping
    public ResponseEntity<Object> getCarts() {
        return handleSuccessfulOkResponse(cartServiceImple.getCarts());
    }

    @PostMapping
    public ResponseEntity<Object> createCart(@Valid @RequestBody CartReqDTO cartReqDTO){
        return handleSuccessfulCreatedResponse(cartServiceImple.createCart(cartReqDTO));
    }

    @PostMapping("{cardId}/items")
    public ResponseEntity<Object> addItemToCart(@PathVariable("cardId") Long cardId, @Valid @RequestBody CartItemReqDTO cartItemReqDTO){
        return handleSuccessfulCreatedResponse(cartServiceImple.addItemToCart(cardId, cartItemReqDTO));
    }

    @PatchMapping("{cardId}/items/{itemId}")
    public ResponseEntity<Object> updateCartItem(@PathVariable("cardId") Long cardId, @PathVariable("itemId") Long itemId, @RequestBody CartItemUpdateReqDTO cartItemUpdateReqDTO){
        return handleSuccessfulOkResponse(cartServiceImple.updateCartItem(cardId, itemId, cartItemUpdateReqDTO));
    }

    @DeleteMapping("{cardId}/items/{itemId}")
    public ResponseEntity<Object> removeItemFromCart(@PathVariable("cardId") Long cardId, @PathVariable("itemId") Long itemId) {
        return handleSuccessfulOkResponse(cartServiceImple.removeItemFromCart(cardId, itemId));
    }

}
