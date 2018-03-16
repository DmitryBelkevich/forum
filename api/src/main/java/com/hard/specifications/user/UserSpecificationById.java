package com.hard.specifications.user;

import com.hard.models.User;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;

public class UserSpecificationById implements Specification<User> {
    private Long id;

    public UserSpecificationById(Long id) {
        this.id = id;
    }

    @Override
    public Predicate toPredicate(Root<User> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
        if (id != null) {
            Path path = root.<Long>get("id");

            Predicate predicate = criteriaBuilder.equal(path, id);

            return predicate;
        }

        return null;
    }
}
