package com.example.test.model.user;

import com.example.test.enums.user.MerchantType;
import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "user",
        uniqueConstraints = {
                            @UniqueConstraint(columnNames = {"mobileNumber", "MerchantType"}),
                            @UniqueConstraint(columnNames = {"email", "MerchantType"}),
                            @UniqueConstraint(columnNames = {"email"})
})
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String firstName;
    private String lastName;
    @Column(nullable = false)
    private String mobileNumber;
    private String email;

    @Column(nullable = false)
    private MerchantType merchantType;
    public User(){}

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public MerchantType getMerchantType() {
        return merchantType;
    }

    public void setMerchantType(MerchantType merchantType) {
        this.merchantType = merchantType;
    }
}