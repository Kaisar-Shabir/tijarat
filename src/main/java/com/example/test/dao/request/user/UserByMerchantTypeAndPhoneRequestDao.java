package com.example.test.dao.request.user;

import com.example.test.enums.user.MerchantType;

public class UserByMerchantTypeAndPhoneRequestDao {
    private String phone;
    private MerchantType merchantType;

    public UserByMerchantTypeAndPhoneRequestDao() {}

    public String getPhone() {
        return phone;
    }

    public MerchantType getMerchantType() {
        return merchantType;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setMerchantType(MerchantType merchantType) {
        this.merchantType = merchantType;
    }
}
