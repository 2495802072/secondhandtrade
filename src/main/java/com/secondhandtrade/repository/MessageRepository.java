package com.secondhandtrade.repository;

import com.secondhandtrade.model.Message;
import com.secondhandtrade.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Long> {

    Message findByMessageId(Long messageId);

    // 根据发送者的 User 实体查询消息
    List<Message> findBySender(User sender);

    // 根据接收者的 User 实体查询消息
    List<Message> findByReceiver(User receiver);

    void deleteByMessageId(Long messageId);
}
