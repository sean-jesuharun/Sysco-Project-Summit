package org.sps.cart_microservice.configuration;

import org.modelmapper.ModelMapper;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
import org.sps.cart_microservice.dto.response.CartItemResDTO;
import org.sps.cart_microservice.entity.CartItem;

@Configuration
public class Config {

    @Bean
    @LoadBalanced
    public WebClient.Builder webClientBuilder() {
        return WebClient.builder();
    }

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();

        // Custom mapping for CartItem to CartItemResDTO.
        modelMapper.createTypeMap(CartItem.class, CartItemResDTO.class).addMappings(mapper -> {
            mapper.map(src -> src.getCartItemKey().getProductId(), CartItemResDTO::setProductId);
        });

        return modelMapper;

    }

}
