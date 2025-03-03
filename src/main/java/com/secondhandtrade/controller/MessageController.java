package com.secondhandtrade.controller;


import com.secondhandtrade.model.Contacts;
import com.secondhandtrade.model.Message;
import com.secondhandtrade.model.User;
import com.secondhandtrade.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/messages")
public class MessageController {
    @Autowired
    private MessageService messageService;

    //查询所有Message
    @GetMapping
    public List<Message> getMessages() {
        return messageService.findAll();
    }

    //新建Message
    @PostMapping
    public ResponseEntity<?> addMessage(@RequestBody Message message) {
        Message saved = messageService.save(message);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    //通过id查询Message
    @GetMapping("/{id}")
    public Message getMessage(@PathVariable Long id) {
        return messageService.findById(id);
    }

    //TODO 通过时间查询Message

    //通过id删除Message
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteMessage(@PathVariable Long id) {
        Message message = messageService.findById(id);
        if (message != null) {
            messageService.deleteByMessageId(id);
            return new ResponseEntity<>(message.getMessageId()+"已删除", HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>("目标未找到",HttpStatus.NOT_FOUND);
        }
    }

    //通过发送方查询Message
    @PostMapping("/sender")
    public ResponseEntity<?> selectBySender(@RequestBody User sender) {
        List<Message> bySender = messageService.findBySender(sender);
        return new ResponseEntity<>(bySender, HttpStatus.OK);
    }

    //通过接收查询Message
    @PostMapping("/receiver")
    public ResponseEntity<?> selectByReceiver(@RequestBody User receiver) {
        List<Message> byReceiver = messageService.findByReceiver(receiver);
        return new ResponseEntity<>(byReceiver, HttpStatus.OK);
    }

    //通过用户和联系对象查询Message
    @PostMapping("/contact")
    public ResponseEntity<?> selectByContact(@RequestBody Contacts contacts) {
        List<Message> messageList = messageService.findBySenderAndReceiver(contacts.getUser(), contacts.getRecipient());
        return new ResponseEntity<>(messageList, HttpStatus.OK);
    }

}
