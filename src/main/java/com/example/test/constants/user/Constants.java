package com.example.test.constants.user;

public final class Constants {
    public static final class ExceptionMessageConstants {
        public static final String MERCHANT_PHONE_ALREADY_EXIST_EXCEPTION_MESSAGE =
                "MerchantAlreadyExist: %s with phone number %s already exists.";
        public static final String USER_MODEL_CONSTRAINT_EXCEPTION_MESSAGE =
                "UserConstraintViolation: %s violates table constraints, {%s}.";
        public static final String USER_WITH_MERCHANT_AND_PHONE_NOT_FOUNT_MESSAGE =
                "UserNotFound: %s with user phone %s not found.";
        public static final String USER_WITH_ID_NOT_FOUNT_MESSAGE =
                "UserNotFound: user with Id %d not found.";
        public static final String USER_WITH_EMAIL_ALREADY_EXIST_MESSAGE =
                "UserWithEmailAlreadyExist: user with email %s already exits.";
    }

    public static final class ResponseConstants {
        public static final String USER_CREATED = "User Created";
        public static final String EMAIL_UPDATED = "Email Updated";
    }
}
