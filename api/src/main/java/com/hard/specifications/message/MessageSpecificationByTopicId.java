package com.hard.specifications.message;

import com.hard.models.Message;
import com.hard.models.Topic;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;

public class MessageSpecificationByTopicId implements Specification<Message> {
    private Long topicId;

    public MessageSpecificationByTopicId(Long topicId) {
        this.topicId = topicId;
    }

    @Override
    public Predicate toPredicate(Root<Message> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
        if (topicId != null) {
            Path path = root.<Topic>get("topic");

            Predicate predicate = criteriaBuilder.equal(path, topicId);

            return predicate;
        }

        return null;
    }
}
