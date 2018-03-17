package com.hard.controllers;

import com.hard.models.User;
import com.hard.services.UserService;
import com.hard.specifications.user.UserSpecificationById;
import com.hard.specifications.user.UserSpecificationByUsername;
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
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<Collection<User>> getAll(
            @RequestParam(name = "id", required = false) Long id,
            @RequestParam(name = "username", required = false) String username
    ) {
        HttpStatus httpStatus;

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN, "*");
        headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_UTF8_VALUE);

        Specification<User> userSpecificationById = new UserSpecificationById(id);
        Specification<User> userSpecificationByUsername = new UserSpecificationByUsername(username);

        Specifications<User> specifications = Specifications
                .where(userSpecificationById)
                .and(userSpecificationByUsername);

        Collection<User> users = userService.getAll(specifications);

        if (!users.isEmpty()) {
            httpStatus = HttpStatus.OK;

            return ResponseEntity
                    .status(httpStatus)
                    .headers(headers)
                    .body(users);
        }

        httpStatus = HttpStatus.NO_CONTENT;

        return ResponseEntity
                .status(httpStatus)
                .headers(headers)
                .body(null);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getById(@PathVariable("id") long id) {
        HttpStatus httpStatus;

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN, "*");
        headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_UTF8_VALUE);

        User u = userService.getById(id);

        if (u != null) {
            httpStatus = HttpStatus.OK;

            return ResponseEntity
                    .status(httpStatus)
                    .headers(headers)
                    .body(u);
        }

        httpStatus = HttpStatus.NOT_FOUND;

        return ResponseEntity
                .status(httpStatus)
                .headers(headers)
                .body(null);
    }

    @PostMapping
    public ResponseEntity<User> add(@RequestBody User user) {
        HttpStatus httpStatus;

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN, "*");
        headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_UTF8_VALUE);

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
        headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_UTF8_VALUE);

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

    @DeleteMapping
    public ResponseEntity<Void> deleteAll() {
        HttpStatus httpStatus = HttpStatus.NO_CONTENT;

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN, "*");
        headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_UTF8_VALUE);

        userService.deleteAll();

        return ResponseEntity
                .status(httpStatus)
                .headers(headers)
                .body(null);
    }
}
