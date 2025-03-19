package com.secondhandtrade.service;

import com.secondhandtrade.model.Contacts;
import com.secondhandtrade.model.User;
import com.secondhandtrade.repository.ContactsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ContactService {
    @Autowired
    private ContactsRepository contactsRepository;

    // 根据用户查询联系人
    public List<Contacts> getContactByUser(User user) {
        return contactsRepository.findByUser(user);
    }

    // 保存联系人
    public void save(Contacts contacts) {
        if (contactsRepository.existsByUserAndRecipient(contacts.getUser(), contacts.getRecipient())) {
            throw new IllegalArgumentException("联系人已存在");
        }
        contacts.setCreatedAt(LocalDateTime.now());
        contactsRepository.save(contacts);
    }

    // 删除联系人
    @Transactional
    public void delete(Contacts contacts) {
        contactsRepository.delete(contacts);
    }
}
