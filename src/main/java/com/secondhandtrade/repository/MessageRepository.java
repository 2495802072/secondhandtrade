package com.secondhandtrade.repository;

import com.secondhandtrade.model.Message;
import com.secondhandtrade.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Long> {

    Message findByMessageId(Long messageId);

    // 根据发送者的 User 实体查询消息
    List<Message> findBySender(User sender);

    // 根据接收者的 User 实体查询消息
    List<Message> findByReceiver(User receiver);

    void deleteByMessageId(Long messageId);

    //遇到的问题/轶闻趣事：这里需要的SQL是能查到两人对话，其中sender和Receiver都有区别，SpringBoot不能自动生成SQL，需要使用@Querry处理SQL语句
    @Query("select m from Message m where m.sender.userId in :ids and m.receiver.userId in :ids order by m.createdAt")
    List<Message> findBySenderIdAndReceiverId(@Param("ids") List<Long> ids);
}
