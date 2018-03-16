package com.hard.specifications.user;

import com.hard.models.User;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;

public class UserSpecificationByUsername implements Specification<User> {
    private String username;

    public UserSpecificationByUsername(String username) {
        this.username = username;
    }

    @Override
    public Predicate toPredicate(Root<User> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
        if (username != null) {
            Path path = root.<String>get("username");

            Predicate predicate = criteriaBuilder.equal(path, username);

            return predicate;
        }

        return null;
    }
}
