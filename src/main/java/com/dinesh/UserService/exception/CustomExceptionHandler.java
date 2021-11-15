package com.dinesh.UserService.exception;

import com.dinesh.UserService.util.ResponseBody;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;

@ControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(HttpClientErrorException.class)
    public ResponseEntity<ResponseBody> clientErrorException(HttpClientErrorException ex) {
        ResponseBody responseBody = new ResponseBody(false, ex.getResponseBodyAsString());
        return new ResponseEntity<>(responseBody, ex.getStatusCode());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseBody> handleException(Exception ex) {
        ResponseBody responseBody = new ResponseBody(false, ex.getMessage(), null);
        return new ResponseEntity<>(responseBody, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
