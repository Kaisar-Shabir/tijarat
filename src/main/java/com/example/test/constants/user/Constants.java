package com.example.test.constants.user;

public final class Constants {
    public static final class ExceptionMessageConstants {
        public static final String MERCHANT_PHONE_ALREADY_EXIST_EXCEPTION_MESSAGE =
                "MerchantAlreadyExist: %s with phone number %s already exists.";
        public static final String USER_MODEL_CONSTRAINT_EXCEPTION_MESSAGE =
                "UserConstraintViolation: %s violates table constraints, {%s}.";
        public static final String USER_WITH_MERCHANT_AND_PHONE_NOT_FOUNT_MESSAGE =
                "UserWithIdNotFound: %s with user phone %s not found.";
    }

    public static final class ResponseConstants {
        public static final String USER_CREATED = "User Created";
    }
}
