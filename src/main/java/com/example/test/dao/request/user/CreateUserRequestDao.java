package com.example.test.dao.request.user;

import com.example.test.enums.user.MerchantType;


public class CreateUserRequestDao {
    private String firstName;
    private String lastName;
    private String mobileNumber;
    private String email;
    private MerchantType merchantType;

    public CreateUserRequestDao() {}

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getMobileNumber() {
        return mobileNumber;
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

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setMerchantType(MerchantType merchantType) {
        this.merchantType = merchantType;
    }
}
