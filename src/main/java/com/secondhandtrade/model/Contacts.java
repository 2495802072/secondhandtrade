package com.secondhandtrade.model;

import com.secondhandtrade.key.ContactsId;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "contacts")
@IdClass(ContactsId.class)
public class Contacts {
    @Id
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id", insertable = false, updatable = false)
    private User user;

    @Id
    @ManyToOne
    @JoinColumn(name = "recipient_id", referencedColumnName = "user_id", insertable = false, updatable = false)
    private User recipient;

    private LocalDateTime createdAt; // 创建时间

    public Contacts() {
    }

    public Contacts(User user, User recipient) {
        this.user = user;
        this.recipient = recipient;
        this.createdAt = LocalDateTime.now();
    }

    // Getter & Setter
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getRecipient() {
        return recipient;
    }

    public void setRecipient(User recipient) {
        this.recipient = recipient;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
