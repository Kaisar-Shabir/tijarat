package com.example.test.exceptions.user;

import com.example.test.exceptions.BaseException;

public class MerchantPhoneAlreadyExistsException extends BaseException {
    public MerchantPhoneAlreadyExistsException(String message) {
        super(message);
    }
}
