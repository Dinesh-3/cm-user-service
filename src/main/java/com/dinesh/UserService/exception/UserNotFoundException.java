package com.dinesh.UserService.exception;

public class UserNotFoundException extends RuntimeException{

    public UserNotFoundException() {
        super("Student Not Found");
    }

    public UserNotFoundException(String message) {
        super(message);
    }
}
