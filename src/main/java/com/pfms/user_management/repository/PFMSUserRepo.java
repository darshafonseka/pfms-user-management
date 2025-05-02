package com.pfms.user_management.repository;

import com.pfms.user_management.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PFMSUserRepo extends JpaRepository<User, Long> {
    User findByEmail(String email);

    User findByUsername(String username);

}

