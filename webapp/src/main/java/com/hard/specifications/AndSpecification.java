package com.hard.specifications;

import java.util.ArrayList;
import java.util.Collection;

public class AndSpecification<T> implements Specification<T> {
    private Collection<Specification> specifications = new ArrayList<>();

    public AndSpecification(Specification... specifications) {
        for (Specification specification : specifications)
            this.specifications.add(specification);
    }

    public void add(Specification specification) {
        specifications.add(specification);
    }

    @Override
    public String getRequest() {
        StringBuilder stringBuilder = new StringBuilder();

        for (Specification specification : specifications)
            stringBuilder.append(specification.getRequest());

        return stringBuilder.toString();
    }
}
