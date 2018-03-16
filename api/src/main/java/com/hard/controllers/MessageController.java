package com.hard.controllers;

import com.hard.models.Message;
import com.hard.services.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Collection<Message>> getAll() {
        HttpStatus httpStatus;

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN, "*");

        Collection<Message> messages = messageService.getAll(null);

        if (messages.isEmpty()) {
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
                .body(messages);
    }

    @GetMapping(value = "/${id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Message> getById(@PathVariable("id") long id) {
        HttpStatus httpStatus;

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN, "*");

        Message m = messageService.getById(id);

        if (m == null) {
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
                .body(m);
    }

    @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Message> add(@RequestBody Message message) {
        HttpStatus httpStatus;

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN, "*");

        Message m = messageService.getById(message.getId());

        if (m != null) {
            httpStatus = HttpStatus.CONFLICT;

            return ResponseEntity
                    .status(httpStatus)
                    .headers(headers)
                    .body(null);
        }

        messageService.save(message);

        httpStatus = HttpStatus.CREATED;

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
}
