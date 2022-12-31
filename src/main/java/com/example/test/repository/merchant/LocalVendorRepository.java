package com.example.test.repository.merchant;

import com.example.test.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocalVendorRepository extends JpaRepository<User, Long> {
}
