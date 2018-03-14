package com.hard.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Set;

@Entity
@Table(name = "users")
public class User extends AbstractModel {
    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

//    @OneToMany(mappedBy = "user")
//    private Set<Topic> topics;

//    @OneToMany(mappedBy = "user")
//    private Set<Message> messages;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

//    public Set<Topic> getTopics() {
//        return topics;
//    }
//
//    public void setTopics(Set<Topic> topics) {
//        this.topics = topics;
//    }

//    public Set<Message> getMessages() {
//        return messages;
//    }
//
//    public void setMessages(Set<Message> messages) {
//        this.messages = messages;
//    }
}
