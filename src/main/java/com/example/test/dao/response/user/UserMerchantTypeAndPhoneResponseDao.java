package com.example.test.dao.response.user;

public class UserMerchantTypeAndPhoneResponseDao {
    private Long userId;
    private String firstName;
    private String email;

    public Long getUserId() {
        return userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getEmail() {
        return email;
    }

    public UserMerchantTypeAndPhoneResponseDao() {}

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
