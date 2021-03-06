package com.hard.controllers;

import com.hard.models.Category;
import com.hard.services.CategoryService;
import com.hard.specifications.category.CategorySpecificationById;
import com.hard.specifications.category.CategorySpecificationByTitle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public ResponseEntity<Collection<Category>> getAll(
            @RequestParam(name = "id", required = false) Long id,
            @RequestParam(name = "title", required = false) String title
    ) {
        HttpStatus httpStatus;

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN, "*");
        headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_UTF8_VALUE);

        Specification<Category> categorySpecificationById = new CategorySpecificationById(id);
        Specification<Category> categorySpecificationByTitle = new CategorySpecificationByTitle(title);

        Specifications<Category> specifications = Specifications
                .where(categorySpecificationById)
                .and(categorySpecificationByTitle);

        Collection<Category> categories = categoryService.getAll(specifications);

        if (!categories.isEmpty()) {
            httpStatus = HttpStatus.OK;

            return ResponseEntity
                    .status(httpStatus)
                    .headers(headers)
                    .body(categories);
        }

        httpStatus = HttpStatus.NO_CONTENT;

        return ResponseEntity
                .status(httpStatus)
                .headers(headers)
                .body(null);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Category> getById(@PathVariable("id") long id) {
        HttpStatus httpStatus;

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN, "*");
        headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_UTF8_VALUE);

        Category c = categoryService.getById(id);

        if (c != null) {
            httpStatus = HttpStatus.OK;

            return ResponseEntity
                    .status(httpStatus)
                    .headers(headers)
                    .body(c);
        }

        httpStatus = HttpStatus.NOT_FOUND;

        return ResponseEntity
                .status(httpStatus)
                .headers(headers)
                .body(null);
    }

    @PostMapping
    public ResponseEntity<Category> add(@RequestBody Category category) {
        HttpStatus httpStatus;

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN, "*");
        headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_UTF8_VALUE);

        Category c = categoryService.getById(category.getId());

        if (c != null)
            httpStatus = HttpStatus.OK;
        else
            httpStatus = HttpStatus.CREATED;

        categoryService.save(category);

        return ResponseEntity
                .status(httpStatus)
                .headers(headers)
                .body(category);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") long id) {
        HttpStatus httpStatus;

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN, "*");
        headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_UTF8_VALUE);

        Category c = categoryService.getById(id);

        if (c != null) {
            httpStatus = HttpStatus.NO_CONTENT;

            categoryService.delete(id);
        } else {
            httpStatus = HttpStatus.NOT_FOUND;
        }

        return ResponseEntity
                .status(httpStatus)
                .headers(headers)
                .body(null);
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteAll() {
        HttpStatus httpStatus = HttpStatus.NO_CONTENT;

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN, "*");
        headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_UTF8_VALUE);

        categoryService.deleteAll();

        return ResponseEntity
                .status(httpStatus)
                .headers(headers)
                .body(null);
    }
}
