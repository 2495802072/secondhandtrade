package com.secondhandtrade.key;

import java.io.Serializable;
import java.util.Objects;

// *遇到的问题/轶闻趣事*：SpringBoot中同时使用外键且同时是复合主键的情况，
// 网上对此资料甚少，大部分人很容易把主键类编写为User user(err：不能是复合类型)或者Long userId(err：定义的主键应当在model类中名称一致)，
// 但是其实是两者折中Long user
public class ContactsId implements Serializable {
    private Long user;
    private Long recipient;

    // 必须提供无参构造函数、equals 和 hashCode 方法
    public ContactsId() {}

    public ContactsId(Long user, Long recipient) {
        this.user = user;
        this.recipient = recipient;
    }

    public Long getUserId() {
        return user;
    }

    public void setUserId(Long user) {
        this.user = user;
    }

    public Long getRecipientId() {
        return recipient;
    }

    public void setRecipientId(Long recipient) {
        this.recipient = recipient;
    }

    // equals 和 hashCode 方法
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContactsId that = (ContactsId) o;
        return Objects.equals(user, that.user) && Objects.equals(recipient, that.recipient);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user, recipient);
    }
}
