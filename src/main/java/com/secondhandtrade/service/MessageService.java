package com.secondhandtrade.service;

import com.secondhandtrade.model.Message;
import com.secondhandtrade.model.User;
import com.secondhandtrade.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Service
public class MessageService {
    @Autowired
    private MessageRepository messageRepository;

    // 保存消息
    public Message save(Message message) {
        message.setCreatedAt(LocalDateTime.now());
        return messageRepository.save(message);
    }

    // 查询所有消息
    public List<Message> findAll() {
        return messageRepository.findAll();
    }

    // 根据ID查询消息
    public Message findById(Long id) {
        return messageRepository.findByMessageId(id);
    }

    // 根据ID删除消息
    @Transactional
    public void deleteByMessageId(Long id) {
        messageRepository.deleteByMessageId(id);
    }

    // 根据发送者查询消息
    public List<Message> findBySender(User sender) {
        return messageRepository.findBySender(sender);
    }

    // 根据接收者查询消息
    public List<Message> findByReceiver(User receiver) {
        return messageRepository.findByReceiver(receiver);
    }

    // 根据发送者和接收者查询消息
    public List<Message> findBySenderAndReceiver(User sender, User receiver) {
        List<Long> ids = Arrays.asList(sender.getUserId(), receiver.getUserId());
        return messageRepository.findBySenderIdAndReceiverId(ids);
    }
}
