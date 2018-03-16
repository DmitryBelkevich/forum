package com.hard.controllers;

import com.hard.models.User;
import com.hard.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Collection<User>> getAll() {
        HttpStatus httpStatus;

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN, "*");

        Collection<User> users = userService.getAll(null);

        if (users.isEmpty()) {
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
                .body(users);
    }

    @GetMapping(value = "/${id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<User> getById(@PathVariable("id") long id) {
        HttpStatus httpStatus;

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN, "*");

        User u = userService.getById(id);

        if (u == null) {
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
                .body(u);
    }

    @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<User> add(@RequestBody User user) {
        HttpStatus httpStatus;

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN, "*");

        User u = userService.getById(user.getId());

        if (u != null) {
            httpStatus = HttpStatus.CONFLICT;

            return ResponseEntity
                    .status(httpStatus)
                    .headers(headers)
                    .body(null);
        }

        userService.save(user);

        httpStatus = HttpStatus.CREATED;

        return ResponseEntity
                .status(httpStatus)
                .headers(headers)
                .body(user);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") long id) {
        HttpStatus httpStatus;

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN, "*");

        User u = userService.getById(id);

        if (u == null) {
            httpStatus = HttpStatus.NOT_FOUND;

            return ResponseEntity
                    .status(httpStatus)
                    .headers(headers)
                    .body(null);
        }

        userService.delete(id);

        httpStatus = HttpStatus.NO_CONTENT;

        return ResponseEntity
                .status(httpStatus)
                .headers(headers)
                .body(null);
    }
}
