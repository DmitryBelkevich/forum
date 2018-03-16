package com.hard.specifications.topic;

import com.hard.models.Topic;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;

public class TopicSpecificationById implements Specification<Topic> {
    private Long id;

    public TopicSpecificationById(Long id) {
        this.id = id;
    }

    @Override
    public Predicate toPredicate(Root<Topic> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
        if (id != null) {
            Path path = root.<Long>get("id");

            Predicate predicate = criteriaBuilder.equal(path, id);

            return predicate;
        }

        return null;
    }
}
