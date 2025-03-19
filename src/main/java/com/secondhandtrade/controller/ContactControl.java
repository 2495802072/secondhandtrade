package com.secondhandtrade.controller;

import com.secondhandtrade.model.Contacts;
import com.secondhandtrade.model.User;
import com.secondhandtrade.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/contact")
public class ContactControl {
    @Autowired
    private ContactService contactService;

    // 添加联系人
    @PostMapping
    public ResponseEntity<String> save(@RequestBody Contacts contacts) {
        try {
            contactService.save(contacts);
            return new ResponseEntity<>("发起聊天", HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }

    // 根据用户查询联系人
    @PostMapping("/user")
    public List<Contacts> getContactByUser(@RequestBody User user) {
        return contactService.getContactByUser(user);
    }

    // 删除联系人
    @DeleteMapping
    public void delete(@RequestBody Contacts contacts) {
        contactService.delete(contacts);
    }
}
