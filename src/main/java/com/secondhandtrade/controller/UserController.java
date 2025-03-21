package com.secondhandtrade.controller;

import com.secondhandtrade.model.LoginResponse;
import com.secondhandtrade.model.User;
import com.secondhandtrade.service.UserService;
import com.secondhandtrade.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;

    // 获取所有用户
    @GetMapping
    public List<User> getUsers() {
        return userService.findAll();
    }

    // 注册用户
    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody User user) {
        try {
            User u = userService.register(user);
            String token = generateToken(u);
            LoginResponse response = new LoginResponse(token, u);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }

    // 用户登录
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user) {
        try {
            User u = userService.login(user.getUsername(), user.getPassword());
            String token = generateToken(u);
            LoginResponse response = new LoginResponse(token, u);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }

    // 根据ID查询用户
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        User u = userService.findByUserId(id);
        if (u == null) {
            return ResponseEntity.notFound().build();
        }
        return new ResponseEntity<>(u, HttpStatus.OK);
    }

    // 根据ID删除用户
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        if (userRepository.existsById(id)) {
            userService.deleteById(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // 根据用户名查询用户
    @GetMapping("/byName/{name}")
    public ResponseEntity<?> getUsersByName(@PathVariable String name) {
        List<User> list = userService.findListByUsername(name);
        if (list.isEmpty()) {
            return new ResponseEntity<>("用户未找到", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(list);
    }

    // 生成Token（示例：使用JWT）
    private String generateToken(User user) {
        // 实际项目中应使用JWT库生成Token
        return String.valueOf(user.getUserId());
    }
}
