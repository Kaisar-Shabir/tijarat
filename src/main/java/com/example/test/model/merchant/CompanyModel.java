package com.example.test.model.merchant;

import com.example.test.model.merchant.BaseMerchant;
import jakarta.persistence.*;

@Entity
@Table(name = "Company",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"Id"})
})
public class CompanyModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "merchantId", referencedColumnName = "id")
    private BaseMerchant merchant;
}
