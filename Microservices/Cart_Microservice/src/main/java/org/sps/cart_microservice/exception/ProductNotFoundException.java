package org.sps.cart_microservice.exception;

import lombok.Getter;
import lombok.Setter;
import org.sps.cart_microservice.exception.model.ErrorResponse;

import java.util.List;
import java.util.Map;

@Getter
@Setter
public class ProductNotFoundException extends RuntimeException{

    private Map<String, List<ErrorResponse>> errorResponseMap;

    public ProductNotFoundException(Map<String, List<ErrorResponse>> errorResponseMap) {
        this.errorResponseMap = errorResponseMap;
    }

}
