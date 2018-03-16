package com.hard.models;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.Set;

public class Topic extends AbstractModel {
    private String title;
    private User user;
    private Set<Message> messages;
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

    public Set<Message> getMessages() {
        return messages;
    }

    public void setMessages(Set<Message> messages) {
        this.messages = messages;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getLastMessageDate() {
        Iterator<Message> iterator = messages.iterator();
        Message message = iterator.next();

        while (iterator.hasNext()) {
            message = iterator.next();
        }

        Date date = message.getDate();

        SimpleDateFormat dateFormat = new SimpleDateFormat("E dd.MM.yyyy HH:mm:ss");

        return dateFormat.format(date);
    }
}
