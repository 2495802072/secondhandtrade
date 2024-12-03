package com.secondhandtrade.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

import com.secondhandtrade.model.User;
import com.secondhandtrade.service.UserService;
import com.secondhandtrade.repository.UserRepository;

@CrossOrigin
@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;

    //获取所有用户
    @GetMapping
    public List<User> getUsers() {
        return userService.findAll();
    }

    //新建用户（用户注册） -- ResponseEntity<?>设置返回的响应体类型不固定，成功返回User，报错返回String报错信息
    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody User user) {
        try{
            User u = userService.register(user);
            //请求正确，返回user
            return new ResponseEntity<>(u, HttpStatus.CREATED);
        }catch(IllegalArgumentException e){
            //错误的请求，返回错误信息
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    //通过id查询user
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        User user = userService.findByUserId(id);

        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(user);
    }

    //通过id删除user
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        if (userRepository.existsById(id)) {
            userService.deleteById(id);
            return ResponseEntity.ok().build();// 静态返回204 表示删除成功
        }
        else {
            return ResponseEntity.notFound().build();// 静态返回404 表示没有该user
        }
    }

    @GetMapping("/byName/{name}")
    public ResponseEntity<?> getUsersByName(@PathVariable String name) {
        List<User> list = userService.findByUsername(name);

        if (list.isEmpty()) {
            return new ResponseEntity<>("用户未找到", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(list);
    }

}
