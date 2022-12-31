package com.example.test.exceptions.user;

import com.example.test.exceptions.BaseException;

public class UserConstraintException extends BaseException {
    public UserConstraintException(String message) {
        super(message);
    }
}
