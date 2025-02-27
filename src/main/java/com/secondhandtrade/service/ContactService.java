package com.secondhandtrade.service;

import com.secondhandtrade.model.Contacts;
import com.secondhandtrade.model.User;
import com.secondhandtrade.repository.ContactsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ContactService {
    @Autowired //不使用implements(实现接口) ，实现松耦合
    private ContactsRepository contactsRepository;

    public List<Contacts> getContactByUser(User user) {
        return contactsRepository.findByUser(user);
    }

    public void save(Contacts contacts) {
        if(contactsRepository.existsByUserAndRecipient(contacts.getUser(),contacts.getRecipient())){
            //抛出异常，用户已经存在该联系人
            throw new IllegalArgumentException("联系人已存在");
        }
        contactsRepository.save(contacts);
    }

    @Transactional
    public void delete(Contacts contacts) {
        contactsRepository.delete(contacts);
    }

}
