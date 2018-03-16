package com.hard.specifications.topic;

import com.hard.models.Topic;
import com.hard.models.User;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;

public class TopicSpecificationByUserId implements Specification<Topic> {
    private Long userId;

    public TopicSpecificationByUserId(Long userId) {
        this.userId = userId;
    }

    @Override
    public Predicate toPredicate(Root<Topic> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
        if (userId != null) {
            Path path = root.<User>get("user");

            Predicate predicate = criteriaBuilder.equal(path, userId);

            return predicate;
        }

        return null;
    }
}
