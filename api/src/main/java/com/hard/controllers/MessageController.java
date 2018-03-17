package com.hard.controllers;

import com.hard.models.Message;
import com.hard.services.MessageService;
import com.hard.specifications.message.MessageSpecificationById;
import com.hard.specifications.message.MessageSpecificationByText;
import com.hard.specifications.message.MessageSpecificationByTopicId;
import com.hard.specifications.message.MessageSpecificationByUserId;
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
@RequestMapping("/api/messages")
public class MessageController {
    @Autowired
    private MessageService messageService;

    @GetMapping
    public ResponseEntity<Collection<Message>> getAll(
            @RequestParam(name = "id", required = false) Long id,
            @RequestParam(name = "text", required = false) String text,
            @RequestParam(name = "topicId", required = false) Long topicId,
            @RequestParam(name = "userId", required = false) Long userId
    ) {
        HttpStatus httpStatus;

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN, "*");
        headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_UTF8_VALUE);

        Specification<Message> messageSpecificationById = new MessageSpecificationById(id);
        Specification<Message> messageSpecificationByText = new MessageSpecificationByText(text);
        Specification<Message> messageSpecificationByTopicId = new MessageSpecificationByTopicId(topicId);
        Specification<Message> messageSpecificationByUserId = new MessageSpecificationByUserId(userId);

        Specifications<Message> specifications = Specifications
                .where(messageSpecificationById)
                .and(messageSpecificationByText)
                .and(messageSpecificationByTopicId)
                .and(messageSpecificationByUserId);

        Collection<Message> messages = messageService.getAll(specifications);

        if (!messages.isEmpty()) {
            httpStatus = HttpStatus.OK;

            return ResponseEntity
                    .status(httpStatus)
                    .headers(headers)
                    .body(messages);
        }

        httpStatus = HttpStatus.NO_CONTENT;

        return ResponseEntity
                .status(httpStatus)
                .headers(headers)
                .body(null);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Message> getById(@PathVariable("id") long id) {
        HttpStatus httpStatus;

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN, "*");
        headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_UTF8_VALUE);

        Message m = messageService.getById(id);

        if (m != null) {
            httpStatus = HttpStatus.OK;

            return ResponseEntity
                    .status(httpStatus)
                    .headers(headers)
                    .body(m);
        }

        httpStatus = HttpStatus.NOT_FOUND;

        return ResponseEntity
                .status(httpStatus)
                .headers(headers)
                .body(null);
    }

    @PostMapping
    public ResponseEntity<Message> add(@RequestBody Message message) {
        HttpStatus httpStatus;

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN, "*");
        headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_UTF8_VALUE);

        Message m = messageService.getById(message.getId());

        if (m != null)
            httpStatus = HttpStatus.OK;
        else
            httpStatus = HttpStatus.CREATED;

        messageService.save(message);

        return ResponseEntity
                .status(httpStatus)
                .headers(headers)
                .body(message);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") long id) {
        HttpStatus httpStatus;

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN, "*");
        headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_UTF8_VALUE);

        Message m = messageService.getById(id);

        if (m == null) {
            httpStatus = HttpStatus.NOT_FOUND;

            return ResponseEntity
                    .status(httpStatus)
                    .headers(headers)
                    .body(null);
        }

        messageService.delete(id);

        httpStatus = HttpStatus.NO_CONTENT;

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

        messageService.deleteAll();

        return ResponseEntity
                .status(httpStatus)
                .headers(headers)
                .body(null);
    }
}
