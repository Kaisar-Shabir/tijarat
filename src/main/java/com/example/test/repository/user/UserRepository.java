package com.example.test.repository.user;

import com.example.test.enums.user.MerchantType;
import com.example.test.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    public User findByMobileNumberAndMerchantType(String mobileNumber, MerchantType merchantType);
}

