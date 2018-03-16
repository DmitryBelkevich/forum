package com.hard.models;

import java.util.Collection;
import java.util.Iterator;

public class Topic extends AbstractModel {
    private String title;
    private User user;
    private Collection<Message> messages;
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

    public Collection<Message> getMessages() {
        return messages;
    }

    public void setMessages(Collection<Message> messages) {
        this.messages = messages;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Message getLastMessage() {
        Iterator<Message> iterator = messages.iterator();
        Message message = iterator.next();

        while (iterator.hasNext())
            message = iterator.next();

        return message;
    }
}
