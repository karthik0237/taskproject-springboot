package com.example.taskproject.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class TaskUserMismatch extends RuntimeException{

    public TaskUserMismatch(String message) {
        super(message);

    }
}
