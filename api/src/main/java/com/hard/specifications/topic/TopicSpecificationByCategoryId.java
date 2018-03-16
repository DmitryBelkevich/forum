package com.hard.specifications.topic;

import com.hard.models.Category;
import com.hard.models.Topic;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;

public class TopicSpecificationByCategoryId implements Specification<Topic> {
    private Long categoryId;

    public TopicSpecificationByCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    @Override
    public Predicate toPredicate(Root<Topic> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
        if (categoryId != null) {
            Path path = root.<Category>get("category");

            Predicate predicate = criteriaBuilder.equal(path, categoryId);

            return predicate;
        }

        return null;
    }
}
