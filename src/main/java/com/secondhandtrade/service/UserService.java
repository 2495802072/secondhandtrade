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
    @Autowired //不使用implements ，实现松耦合
    private UserRepository userRepository;
    //查全部
    public List<User> findAll() {
        return userRepository.findAll();
    }
    //注册user信息
    public User register(User user) {
        User oldUser = findByUserId(user.getUserId());

        // 如果不存在ID
        if(oldUser == null) {
            //判断email唯一
            if (userRepository.existsByEmail(user.getEmail())){
                //抛出异常：email参数非法
                throw new IllegalArgumentException("邮箱已注册 (Email already exists)");
            }

            //判断username唯一
            if (userRepository.existsByUsername(user.getUsername())){
                throw new IllegalArgumentException("用户名已存在 (Username already exists)");
            }
            user.setPassword(getSaltedHash(user.getPassword()));

            //获取时间
            user.setCreatedAt(LocalDateTime.now());

        }else{
            //存在用户但是不修改密码 - 密码空
            if(user.getPassword().isEmpty()){
                //设置为原密码
                user.setPassword(oldUser.getPassword());
            }else{
                //否则为新密码加密
                user.setPassword(getSaltedHash(user.getPassword()));
            }
            user.setCreatedAt(oldUser.getCreatedAt());
        }

        user.setUpdatedAt(LocalDateTime.now());

        //返回注册的用户信息
        return userRepository.save(user);
    }

    //查user -- id
    public User findByUserId(Long id) {
        return userRepository.findByUserId(id);
    }

    //查users -- name
    public List<User> findListByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    //查单一User
    public User getByUsername(String username) {
        return userRepository.getByUsername(username);
    }

    //删除user -- id
    @Transactional //保证事务完整处理
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

    //用户登录
    public User login(String username, String password) {
        User u = getByUsername(username);
        if(u == null) {
            throw new IllegalArgumentException("用户未注册 (User not exists)");
        }
        if(!u.getPassword().equals(getSaltedHash(password))) {
            throw new IllegalArgumentException("密码错误 (Password was wrong)");
        }

        return userRepository.login(username,getSaltedHash(password));
//        return userRepository.login(username,password); // 测试加密用
    }

    //passWord加密
    // 123456 -> 8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92
    public static String getSaltedHash(String data) {
        //获取加密密码
        MessageDigest md;
        try {
            md = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            System.out.println("加密算法获取失败");
            throw new RuntimeException(e);
        }
        byte[] digest = md.digest(data.getBytes());
        //加密后的密码
        StringBuilder sb = new StringBuilder();
        for (byte b : digest) {
            String hex = Integer.toHexString(b & 0xff);//与1111 1111做位与运算
            //保证 0~9在转换16进制后仍是2位数
            if (hex.length() == 1) {
                hex = '0' + hex;
            }
            sb.append(hex);
        }
        return sb.toString();
    }
}
