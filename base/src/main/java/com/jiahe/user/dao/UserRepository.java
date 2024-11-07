package com.jiahe.user.dao;

import com.jiahe.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

    User findByUsername(String username);

    Boolean existsByUsername(String username);

    @Query("from User where (username = :account or phone = :account or email = :account) and password = :password")
    User findByAccountAndPassword(@Param("account") String account, @Param("password") String password);
}
