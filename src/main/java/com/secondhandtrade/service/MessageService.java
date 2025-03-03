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

    //新建Message
    public Message save(Message message) {
        message.setCreatedAt(LocalDateTime.now());
        return messageRepository.save(message);
    }

    //查询所有Message
    public List<Message> findAll() {
        return messageRepository.findAll();
    }

    //通过id查找Message
    public Message findById(Long id) {return messageRepository.findByMessageId(id);}

    @Transactional
    //通过id删除Message
    public void deleteByMessageId(Long id) {
        messageRepository.deleteByMessageId(id);
    }

    //通过Sender查找Message
    public List<Message> findBySender(User sender) {
        return messageRepository.findBySender(sender);
    }

    //通过 Receiver 查找Message
    public List<Message> findByReceiver(User receiver) {
        return messageRepository.findByReceiver(receiver);
    }

    //通过Sender & Receiver 查找Message
    public List<Message> findBySenderAndReceiver(User sender, User receiver) {
        // 将 sender 和 receiver 的 userId 放入 List
        List<Long> ids = Arrays.asList(sender.getUserId(), receiver.getUserId());

        // 调用 Repository 方法
        return messageRepository.findBySenderIdAndReceiverId(ids);
    }

}
