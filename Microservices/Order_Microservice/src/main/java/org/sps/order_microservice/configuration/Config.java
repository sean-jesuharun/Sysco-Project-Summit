package org.sps.order_microservice.configuration;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.sps.order_microservice.dto.response.OrderItemResDTO;
import org.sps.order_microservice.entity.OrderItem;

@Configuration
public class Config {

    @Bean
    public ModelMapper modelMapper() {

        ModelMapper modelMapper = new ModelMapper();

        // Custom mapping for OrderItem to OrderItemResDTO.
        modelMapper.typeMap(OrderItem.class, OrderItemResDTO.class).addMappings(mapper -> {
            mapper.map(src -> src.getOrderItemKey().getProductId(), OrderItemResDTO::setProductId);
        });

//        // Custom mapping for OrderItemResDTO to OrderItem.
//        modelMapper.typeMap(OrderItemResDTO.class, OrderItem.class).addMappings(mapper -> {
//            mapper.map(OrderItemResDTO::getProductId, (dest, value) -> dest.getOrderItemKey().setProductId((Long) value));
//        });

        return modelMapper;
    }

}
