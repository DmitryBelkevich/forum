package com.hard.specifications.topic;

import com.hard.models.Topic;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;

public class TopicSpecificationByTitle implements Specification<Topic> {
    private String title;

    public TopicSpecificationByTitle(String title) {
        this.title = title;
    }

    @Override
    public Predicate toPredicate(Root<Topic> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
        if (title != null) {
            Path path = root.<String>get("title");

            Predicate predicate = criteriaBuilder.equal(path, title);

            return predicate;
        }

        return null;
    }
}
