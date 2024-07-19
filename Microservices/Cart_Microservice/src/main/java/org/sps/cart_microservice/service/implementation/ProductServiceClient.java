package org.sps.cart_microservice.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.sps.cart_microservice.dto.response.ProductResDTO;
import org.sps.cart_microservice.exception.ProductNotFoundException;
import org.sps.cart_microservice.exception.model.ErrorResponse;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;

@Service
public class ProductServiceClient {

    private WebClient webClient;

    @Autowired
    public ProductServiceClient(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("http://Product-MicroService-SPS/products").build();
    }

    // Get Product by using its ID
    public ProductResDTO getProductById(Long productId) {

        return webClient.get()
                .uri("/{productId}", productId)
                .retrieve()
                .onStatus(
                        HttpStatusCode::is4xxClientError,
                        response -> {
                            // Handle 404 StatusCode
                            if (response.statusCode().isSameCodeAs(HttpStatus.NOT_FOUND)){
                                return response.bodyToMono(new ParameterizedTypeReference<Map<String, List<ErrorResponse>>>() {})
                                        .flatMap(errorResponseMap -> Mono.error(new ProductNotFoundException(errorResponseMap)));
                            } else {
                                // Handle other 4xx errors
                                // For example, log or throw a different exception
                                return response.bodyToMono(String.class)
                                        .flatMap(body -> Mono.error(new RuntimeException("Unexpected 4xx error: " + body)));
                            }

                        }
                )
                .bodyToMono(ProductResDTO.class)
                .block();

    }

}
