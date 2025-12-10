package com.example.taskproject.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND) // 404 error
public class UserNotFound extends RuntimeException{

    private String message;

    public UserNotFound(String message){
        super(message); // constructs new RuntimeException with given message
        this.message = message;
    }

}
