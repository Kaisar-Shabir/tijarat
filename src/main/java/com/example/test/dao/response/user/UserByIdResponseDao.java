package com.example.test.dao.response.user;

import com.example.test.enums.user.MerchantType;

public class UserByIdResponseDao {
    private String firstName;
    private String phone;
    private String email;
    private MerchantType merchantType;

    public UserByIdResponseDao() {
    }

    public String getFirstName() {
        return firstName;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public MerchantType getMerchantType() {
        return merchantType;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setMerchantType(MerchantType merchantType) {
        this.merchantType = merchantType;
    }
}
