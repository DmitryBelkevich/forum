package com.hard.models;

import java.util.Set;

public class Category extends AbstractModel {
    private String title;
    private Set<Topic> topics;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Set<Topic> getTopics() {
        return topics;
    }

    public void setTopics(Set<Topic> topics) {
        this.topics = topics;
    }
}
