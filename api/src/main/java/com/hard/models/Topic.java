package com.hard.models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "topics")
public class Topic extends AbstractModel {
    @Column(name = "title")
    private String title;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

//    @OneToMany(mappedBy = "topic")
//    private Set<Message> messages;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

//    public Set<Message> getMessages() {
//        return messages;
//    }
//
//    public void setMessages(Set<Message> messages) {
//        this.messages = messages;
//    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
