package com.secondhandtrade.service;

import com.secondhandtrade.model.User;
import com.secondhandtrade.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class UserService {
    @Autowired //不使用implements ，实现松耦合
    private UserRepository userRepository;
    //查全部
    public List<User> findAll() {
        return userRepository.findAll();
    }
    //注册user信息
    public User register(User user) {

        //判断email唯一
        if (userRepository.existsByEmail(user.getEmail())){
            //抛出异常：email参数非法
            throw new IllegalArgumentException("Email already exists(邮箱已注册)");
        }

        //判断username唯一
        if (userRepository.existsByUsername(user.getUsername())){
            throw new IllegalArgumentException("Username already exists(用户名已存在)");
        }

        //获取时间
        user.setCreatedAt(LocalDateTime.now());
        user.setUpdatedAt(LocalDateTime.now());

        //返回注册的用户信息
        return userRepository.save(user);
    }
    //查user -- id
    public User findByUserId(Long id) {
        return userRepository.findByUserId(id);
    }

    //查users -- name
    public List<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    //删除user -- id
    @Transactional //保证事务完整处理
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }
}
