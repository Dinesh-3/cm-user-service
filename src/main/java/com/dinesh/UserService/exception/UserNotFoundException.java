package com.dinesh.UserService.exception;

import org.springframework.http.HttpStatus;

public class UserNotFoundException extends ClientErrorException{
    private static final HttpStatus STATUS = HttpStatus.NOT_FOUND;
    public UserNotFoundException() {
        super(STATUS,"Student Not Found");
    }

    public UserNotFoundException(String message) {
        super(STATUS, message);
    }
}
