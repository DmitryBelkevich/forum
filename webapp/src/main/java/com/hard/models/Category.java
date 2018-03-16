package com.hard.models;

import java.util.Collection;

public class Category extends AbstractModel {
    private String title;
    private Collection<Topic> topics;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Collection<Topic> getTopics() {
        return topics;
    }

    public void setTopics(Collection<Topic> topics) {
        this.topics = topics;
    }
}
