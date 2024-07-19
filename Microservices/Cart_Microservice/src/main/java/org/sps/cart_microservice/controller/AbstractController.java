package org.sps.cart_microservice.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public abstract class AbstractController {

    public ResponseEntity<Object> handleSuccessfulOkResponse(Object response){
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    public ResponseEntity<Object> handleSuccessfulCreatedResponse(Object response){
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    public ResponseEntity<HttpStatus> handleSuccessfulNoContentResponse(){
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    public ResponseEntity<Object> handleClientErrorBadRequestResponse(Object response){
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<Object> handleClientErrorNotFoundResponse(Object response){
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<Object> handleClientErrorUnprocessableEntityResponse(Object response){
        return new ResponseEntity<>(response, HttpStatus.UNPROCESSABLE_ENTITY);
    }

    public ResponseEntity<Object> handleServerErrorResponse(Object response){
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
