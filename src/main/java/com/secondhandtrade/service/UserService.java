package com.secondhandtrade.service;

import com.secondhandtrade.model.User;
import com.secondhandtrade.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    // 查询所有用户
    public List<User> findAll() {
        return userRepository.findAll();
    }

    // 注册用户
    public User register(User user) {
        User oldUser = findByUserId(user.getUserId());

        // 如果用户不存在
        if (oldUser == null) {
            // 检查邮箱是否已注册
            if (userRepository.existsByEmail(user.getEmail())) {
                throw new IllegalArgumentException("邮箱已注册 (Email already exists)");
            }

            // 检查用户名是否已存在
            if (userRepository.existsByUsername(user.getUsername())) {
                throw new IllegalArgumentException("用户名已存在 (Username already exists)");
            }

            // 加密密码
            user.setPassword(getSaltedHash(user.getPassword()));

            // 设置创建时间
            user.setCreatedAt(LocalDateTime.now());
        } else {
            // 如果用户存在且未修改密码
            if (user.getPassword().isEmpty()) {
                user.setPassword(oldUser.getPassword());
            } else {
                // 加密新密码
                user.setPassword(getSaltedHash(user.getPassword()));
            }
            user.setCreatedAt(oldUser.getCreatedAt());
        }

        // 设置更新时间
        user.setUpdatedAt(LocalDateTime.now());

        // 保存用户信息
        return userRepository.save(user);
    }

    // 根据ID查询用户
    public User findByUserId(Long id) {
        return userRepository.findByUserId(id);
    }

    // 根据用户名模糊查询用户列表
    public List<User> findListByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    // 根据用户名查询单个用户
    public User getByUsername(String username) {
        return userRepository.getByUsername(username);
    }

    // 根据ID删除用户
    @Transactional
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

    // 用户登录
    public User login(String username, String password) {
        User u = getByUsername(username);
        if (u == null) {
            throw new IllegalArgumentException("用户未注册 (User not exists)");
        }
        if (!u.getPassword().equals(getSaltedHash(password))) {
            throw new IllegalArgumentException("密码错误 (Password was wrong)");
        }

        return userRepository.login(username, getSaltedHash(password));
    }

    // 密码加密
    public static String getSaltedHash(String data) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] digest = md.digest(data.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b : digest) {
                String hex = Integer.toHexString(b & 0xff);
                if (hex.length() == 1) {
                    hex = '0' + hex;
                }
                sb.append(hex);
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("加密算法获取失败", e);
        }
    }
}
