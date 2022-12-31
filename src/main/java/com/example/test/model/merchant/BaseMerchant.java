package com.example.test.model.merchant;

import com.example.test.enums.merchant.MerchantStatus;
import com.example.test.model.user.User;
import jakarta.persistence.*;

@Entity
@Table(name = "Merchant",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"gstNumber"})}
)
public class BaseMerchant {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "userId", referencedColumnName = "id")
    private User user;
    @Column(nullable = false)
    private Integer gstNumber;
    @Column(nullable = false)
    private MerchantStatus merchantStatus;

    public BaseMerchant() {}

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Integer getGstNumber() {
        return gstNumber;
    }

    public void setGstNumber(Integer gstNumber) {
        this.gstNumber = gstNumber;
    }

    public MerchantStatus getMerchantStatus() {
        return merchantStatus;
    }

    public void setMerchantStatus(MerchantStatus merchantStatus) {
        this.merchantStatus = merchantStatus;
    }

}
