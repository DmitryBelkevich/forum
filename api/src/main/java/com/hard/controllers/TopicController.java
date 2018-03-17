package com.hard.controllers;

import com.hard.models.Topic;
import com.hard.services.TopicService;
import com.hard.specifications.topic.TopicSpecificationByCategoryId;
import com.hard.specifications.topic.TopicSpecificationById;
import com.hard.specifications.topic.TopicSpecificationByTitle;
import com.hard.specifications.topic.TopicSpecificationByUserId;
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
@RequestMapping("/api/topics")
public class TopicController {
    @Autowired
    private TopicService topicService;

    @GetMapping("")
    public ResponseEntity<Collection<Topic>> getAll(
            @RequestParam(name = "id", required = false) Long id,
            @RequestParam(name = "title", required = false) String title,
            @RequestParam(name = "categoryId", required = false) Long categoryId,
            @RequestParam(name = "userId", required = false) Long userId
    ) {
        HttpStatus httpStatus;

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN, "*");
        headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_UTF8_VALUE);

        Specification<Topic> topicSpecificationById = new TopicSpecificationById(id);
        Specification<Topic> topicSpecificationByTitle = new TopicSpecificationByTitle(title);
        Specification<Topic> topicSpecificationByCategoryId = new TopicSpecificationByCategoryId(categoryId);
        Specification<Topic> topicSpecificationByUserId = new TopicSpecificationByUserId(userId);

        Specifications<Topic> specifications = Specifications
                .where(topicSpecificationById)
                .and(topicSpecificationByTitle)
                .and(topicSpecificationByCategoryId)
                .and(topicSpecificationByUserId);

        Collection<Topic> topics = topicService.getAll(specifications);

        if (topics.isEmpty()) {
            httpStatus = HttpStatus.NO_CONTENT;

            return ResponseEntity
                    .status(httpStatus)
                    .headers(headers)
                    .body(null);
        }

        httpStatus = HttpStatus.OK;

        return ResponseEntity
                .status(httpStatus)
                .headers(headers)
                .body(topics);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Topic> getById(@PathVariable("id") long id) {
        HttpStatus httpStatus;

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN, "*");
        headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_UTF8_VALUE);

        Topic t = topicService.getById(id);

        if (t == null) {
            httpStatus = HttpStatus.NOT_FOUND;

            return ResponseEntity
                    .status(httpStatus)
                    .headers(headers)
                    .body(null);
        }

        httpStatus = HttpStatus.OK;

        return ResponseEntity
                .status(httpStatus)
                .headers(headers)
                .body(t);
    }

    @PostMapping("")
    public ResponseEntity<Topic> add(@RequestBody Topic Topic) {
        HttpStatus httpStatus;

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN, "*");
        headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_UTF8_VALUE);

        Topic t = topicService.getById(Topic.getId());

        if (t != null) {
            httpStatus = HttpStatus.CONFLICT;

            return ResponseEntity
                    .status(httpStatus)
                    .headers(headers)
                    .body(null);
        }

        topicService.save(Topic);

        httpStatus = HttpStatus.CREATED;

        return ResponseEntity
                .status(httpStatus)
                .headers(headers)
                .body(Topic);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") long id) {
        HttpStatus httpStatus;

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN, "*");
        headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_UTF8_VALUE);

        Topic t = topicService.getById(id);

        if (t == null) {
            httpStatus = HttpStatus.NOT_FOUND;

            return ResponseEntity
                    .status(httpStatus)
                    .headers(headers)
                    .body(null);
        }

        topicService.delete(id);

        httpStatus = HttpStatus.NO_CONTENT;

        return ResponseEntity
                .status(httpStatus)
                .headers(headers)
                .body(null);
    }

    @DeleteMapping("")
    public ResponseEntity<Void> deleteAll() {
        HttpStatus httpStatus = HttpStatus.NO_CONTENT;

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN, "*");
        headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_UTF8_VALUE);

//        topicService.deleteAll();

        return ResponseEntity
                .status(httpStatus)
                .headers(headers)
                .body(null);
    }
}
