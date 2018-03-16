package com.hard.specifications.message;

import com.hard.models.Message;
import com.hard.models.User;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;

public class MessageSpecificationByUserId implements Specification<Message> {
    private Long userId;

    public MessageSpecificationByUserId(Long userId) {
        this.userId = userId;
    }

    @Override
    public Predicate toPredicate(Root<Message> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
        if (userId != null) {
            Path path = root.<User>get("user");

            Predicate predicate = criteriaBuilder.equal(path, userId);

            return predicate;
        }

        return null;
    }
}
