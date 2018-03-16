package com.hard.specifications.message;

import com.hard.models.Message;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;

public class MessageSpecificationByText implements Specification<Message> {
    private String text;

    public MessageSpecificationByText(String text) {
        this.text = text;
    }

    @Override
    public Predicate toPredicate(Root<Message> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
        if (text != null) {
            Path path = root.<String>get("text");

            Predicate predicate = criteriaBuilder.equal(path, text);

            return predicate;
        }

        return null;
    }
}
