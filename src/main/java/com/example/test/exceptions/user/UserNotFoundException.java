package com.example.test.exceptions.user;

import com.example.test.exceptions.BaseException;

public class UserNotFoundException extends BaseException {
    public UserNotFoundException(String message) {
        super(message);
    }
}
