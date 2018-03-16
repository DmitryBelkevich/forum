package com.hard.specifications.category;

import com.hard.models.Category;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;

public class CategorySpecificationByTitle implements Specification<Category> {
    private String title;

    public CategorySpecificationByTitle(String title) {
        this.title = title;
    }

    @Override
    public Predicate toPredicate(Root<Category> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
        if (title != null) {
            Path path = root.<String>get("title");

            Predicate predicate = criteriaBuilder.equal(path, title);

            return predicate;
        }

        return null;
    }
}
