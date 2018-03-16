package com.hard.specifications.category;

import com.hard.models.Category;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;

public class CategorySpecificationById implements Specification<Category> {
    private Long id;

    public CategorySpecificationById(Long id) {
        this.id = id;
    }

    @Override
    public Predicate toPredicate(Root<Category> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
        if (id != null) {
            Path path = root.<Long>get("id");

            Predicate predicate = criteriaBuilder.equal(path, id);

            return predicate;
        }

        return null;
    }
}
