package com.secondhandtrade.repository;

import com.secondhandtrade.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    //判断是否存在
    boolean existsByEmail(String email);
    boolean existsByUsername(String username);

    @Query("select u from User u where u.username lIKE %?1%")
    List<User> findByUsername(String username);

    User findByUserId(long id);


}
