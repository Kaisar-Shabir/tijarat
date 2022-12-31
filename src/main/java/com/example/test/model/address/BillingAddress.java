package com.example.test.model.address;

import com.example.test.model.merchant.BaseMerchant;
import jakarta.persistence.*;

@Entity
@Table(name = "billingAddress")
public class BillingAddress {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "merchantId", referencedColumnName = "id")
    private BaseMerchant merchant;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "addressId", referencedColumnName = "id")
    private Address address;

    public BillingAddress() {}
}
