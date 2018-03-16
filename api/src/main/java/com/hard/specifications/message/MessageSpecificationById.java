package com.hard.specifications.message;

import com.hard.models.Category;
import com.hard.models.Message;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;

public class MessageSpecificationById implements Specification<Message> {
    private Long id;

    public MessageSpecificationById(Long id) {
        this.id = id;
    }

    @Override
    public Predicate toPredicate(Root<Message> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
        if (id != null) {
            Path path = root.<Long>get("id");

            Predicate predicate = criteriaBuilder.equal(path, id);

            return predicate;
        }

        return null;
    }
}
