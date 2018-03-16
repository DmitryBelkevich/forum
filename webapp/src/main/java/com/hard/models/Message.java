package com.hard.models;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Message extends AbstractModel {
    private String text;
    private Topic topic;
    private User user;
    private Date date;

    public Message() {
    }

    public Message(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Topic getTopic() {
        return topic;
    }

    public void setTopic(Topic topic) {
        this.topic = topic;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getFormattedDate() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("E dd.MM.yyyy HH:mm:ss");

        return dateFormat.format(date);
    }
}
