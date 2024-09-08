package org.sps.cart_microservice.service.implementation;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.sps.cart_microservice.dto.request.CartItemReqDTO;
import org.sps.cart_microservice.dto.request.CartItemUpdateReqDTO;
import org.sps.cart_microservice.dto.response.CartItemResDTO;
import org.sps.cart_microservice.entity.Cart;
import org.sps.cart_microservice.entity.CartItem;
import org.sps.cart_microservice.entity.CartItemKey;
import org.sps.cart_microservice.repository.CartItemRepository;
import org.sps.cart_microservice.service.CartItemService;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class CartItemServiceImple implements CartItemService {

    private CartItemRepository cartItemRepository;

    private ModelMapper modelMapper;

    @Autowired
    public CartItemServiceImple(CartItemRepository cartItemRepository, ModelMapper modelMapper) {
        this.cartItemRepository = cartItemRepository;
        this.modelMapper = modelMapper;
    }

    // Create CartItem
    // Handling if the user add the same item multiple times. (Problem caused when the same item with different memory address is created and added to the list manually)
    public CartItem createCartItem(Cart cart, CartItemReqDTO cartItemReqDTO) {

        // Checking whether the item is already added to the card before adding it to the next time.
        // If it is added already get the cardItem and modify it rather than creating a new one.
        CartItem cartItem = cartItemRepository.findById(CartItemKey.builder()
                        .productId(cartItemReqDTO.getProductId())
                        .cart(cart)
                        .build())
                .orElse(null);

        // If CardItem is not Available Create new One
        if (cartItem == null) {
            // Building CartItem using cart and CartItemReqDTO
            cartItem = CartItem.builder()
                    .cartItemKey(CartItemKey.builder()
                            .productId(cartItemReqDTO.getProductId())
                            .cart(cart)
                            .build())
                    .quantity(cartItemReqDTO.getQuantity())
                    .unitPrice(cartItemReqDTO.getUnitPrice())
                    .subtotal(cartItemReqDTO.getUnitPrice() * cartItemReqDTO.getQuantity())
                    .build();

            // Saving CartItem
            cartItemRepository.save(cartItem);

            // Returning the New cartItem.
            return cartItem;

        }

        cartItem.setQuantity(cartItemReqDTO.getQuantity());
        cartItem.setUnitPrice(cartItemReqDTO.getUnitPrice());
        cartItem.setSubtotal(cartItemReqDTO.getUnitPrice() * cartItemReqDTO.getQuantity());

        // Return Updated CartItem
        return cartItem;

    }

    // Update CartItem
    public void updateCartItem(CartItemKey cartItemKey, CartItemUpdateReqDTO cartItemUpdateReqDTO) {

        // Fetch the CartItem from the db using CartItemKey Else Throw Exception
        CartItem cartItem = cartItemRepository.findById(cartItemKey)
                .orElseThrow(() -> new NoSuchElementException("CartItem Not Found with Id : " + cartItemKey.getProductId()));

        // Updating the quantity
        cartItem.setQuantity(cartItemUpdateReqDTO.getQuantity());

        // Updating the subTotal
        cartItem.setSubtotal(cartItem.getUnitPrice() * cartItemUpdateReqDTO.getQuantity());

    }

    // Delete CartItem
    public CartItem deleteCartItem(CartItemKey cartItemKey) {

        // Fetch the CartItem from the db using CartItemKey Else Throw Exception
        CartItem cartItem = cartItemRepository.findById(cartItemKey)
                .orElseThrow(() -> new NoSuchElementException("CartItem Not Found with Id : " + cartItemKey.getProductId()));

        // Deleting cartItem
        cartItemRepository.deleteById(cartItemKey);

        // Returning the cartItem.
        return cartItem;

    }
}
