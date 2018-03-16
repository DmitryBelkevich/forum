package com.hard.specifications.topic;

import com.hard.models.Topic;
import com.hard.specifications.Specification;

public class TopicSpecificationByCategoryId implements Specification<Topic> {
    private Long categoryId;

    public TopicSpecificationByCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    @Override
    public String getRequest() {
        return "categoryId=" + categoryId;
    }
}
