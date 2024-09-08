package org.sps.cart_microservice.service.implementation;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.sps.cart_microservice.dto.request.CartItemReqDTO;
import org.sps.cart_microservice.dto.request.CartItemUpdateReqDTO;
import org.sps.cart_microservice.dto.request.CartReqDTO;
import org.sps.cart_microservice.dto.response.CartResDTO;
import org.sps.cart_microservice.dto.response.ProductResDTO;
import org.sps.cart_microservice.entity.Cart;
import org.sps.cart_microservice.entity.CartItem;
import org.sps.cart_microservice.entity.CartItemKey;
import org.sps.cart_microservice.repository.CartItemRepository;
import org.sps.cart_microservice.repository.CartRepository;
import org.sps.cart_microservice.service.CartService;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class CartServiceImple implements CartService {

    private CartRepository cartRepository;

    private CartItemServiceImple cartItemServiceImple;

    private ProductServiceClient productServiceClient;

    @PersistenceContext
    private EntityManager entityManager;

    private ModelMapper modelMapper;

    @Autowired
    public CartServiceImple(CartRepository cartRepository, CartItemServiceImple cartItemServiceImple, ProductServiceClient productServiceClient, EntityManager entityManager, ModelMapper modelMapper) {
        this.cartRepository = cartRepository;
        this.cartItemServiceImple = cartItemServiceImple;
        this.productServiceClient = productServiceClient;
        this.entityManager = entityManager;
        this.modelMapper = modelMapper;
    }

    // Get Carts
    @Transactional
    public List<CartResDTO> getCarts() {

        // Fetching all Carts from db
        // Mapping Cart to CartResDTO
        return cartRepository.findAll().stream()
               .map(cart -> modelMapper.map(cart, CartResDTO.class))
               .toList();

    }

    // Create Cart
    @Transactional
    public CartResDTO createCart(CartReqDTO cartReqDTO) {

        // Building a Cart Transient Entity from CartReqDTO
        Cart cart = Cart.builder()
                .userId(cartReqDTO.getUserId())
                .creationTime(OffsetDateTime.now())
                .lastUpdateTime(OffsetDateTime.now())
                .build();

        // Saving the cart
        cartRepository.save(cart);

        // Returning the Cart as a CartResDTO
        return modelMapper.map(cart, CartResDTO.class);

    }

    @Transactional
    public CartResDTO addItemToCart(Long cardId, CartItemReqDTO cartItemReqDTO) {

        // Fetch the Product Details from Product_Micro-Service using ProductId
        ProductResDTO product = productServiceClient.getProductById(cartItemReqDTO.getProductId());

        // Setting the UnitPrice retrieved from the Product_Micro-Service
        cartItemReqDTO.setUnitPrice(product.getPrice());

        // Fetching the Cart from db using CartId Else Throw Exception
        Cart cart = cartRepository.findById(cardId)
                .orElseThrow(() -> new NoSuchElementException("Cart Not Found with Id : " + cardId));

        // Create CardItem
        // Adding the cartItem to the Cart items List.
        cart.getItems().add(cartItemServiceImple.createCartItem(cart, cartItemReqDTO));

        // Update the TotalPrice based on items in the cart.
        cart.setTotalPrice(calculateTotalPrice(cart.getItems()));

        // Update the LastUpdatedTime of cart.
        cart.setLastUpdateTime(OffsetDateTime.now());

        // Returning the Cart as a CartResDTO
        return modelMapper.map(cart, CartResDTO.class);

    }

    @Transactional
    public CartResDTO updateCartItem(Long cardId, Long itemId, CartItemUpdateReqDTO cartItemUpdateReqDTO) {

        // Fetching the Cart from db using CartId Else Throw Exception
        Cart cart = cartRepository.findById(cardId)
                .orElseThrow(() -> new NoSuchElementException("Cart Not Found with Id : " + cardId));

        // Update CardItem
        cartItemServiceImple.updateCartItem(CartItemKey.builder()
                .productId(itemId)
                .cart(cart)
                .build(), cartItemUpdateReqDTO);

        // Update the TotalPrice based on items in the cart.
        cart.setTotalPrice(calculateTotalPrice(cart.getItems()));

        // Update the LastUpdatedTime of cart.
        cart.setLastUpdateTime(OffsetDateTime.now());

        // Returning the Cart as a CartResDTO
        return modelMapper.map(cart, CartResDTO.class);
    }

    @Transactional
    public CartResDTO removeItemFromCart(Long cardId, Long itemId) {

        // Fetching the Cart from db using CartId Else Throw Exception
        Cart cart = cartRepository.findById(cardId)
                .orElseThrow(() -> new NoSuchElementException("Cart Not Found with Id : " + cardId));

        // Delete CardItem
        // Removing cartItem from the Cart items List.
        cart.getItems().remove(cartItemServiceImple.deleteCartItem(CartItemKey.builder()
                .productId(itemId)
                .cart(cart)
                .build()));

        // Update the TotalPrice based on items in the cart.
        cart.setTotalPrice(calculateTotalPrice(cart.getItems()));

        // Update the LastUpdatedTime of cart.
        cart.setLastUpdateTime(OffsetDateTime.now());

        // Returning the Cart as a CartResDTO
        return modelMapper.map(cart, CartResDTO.class);

    }

    // Calculate the TotalPrice based on the items currently in the Cart
    public Double calculateTotalPrice(List<CartItem> items) {

        return items.stream()
                .mapToDouble(CartItem::getSubtotal)
                .sum();

    }

}
