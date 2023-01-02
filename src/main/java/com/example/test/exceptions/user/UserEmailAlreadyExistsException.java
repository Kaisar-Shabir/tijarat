package com.example.test.exceptions.user;

import com.example.test.exceptions.BaseException;

public class UserEmailAlreadyExistsException extends BaseException {
    public UserEmailAlreadyExistsException(String message) {
        super(message);
    }
}
