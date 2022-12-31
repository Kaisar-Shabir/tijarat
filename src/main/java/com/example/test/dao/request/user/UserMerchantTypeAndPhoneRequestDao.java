package com.example.test.dao.request.user;

import com.example.test.enums.user.MerchantType;

public class UserMerchantTypeAndPhoneRequestDao {
    private String phone;
    private MerchantType merchantType;

    public UserMerchantTypeAndPhoneRequestDao() {}

    public String getPhone() {
        return phone;
    }

    public MerchantType getMerchantType() {
        return merchantType;
    }
}
