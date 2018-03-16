package com.hard.controllers;

import com.hard.services.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class MainController {
    @GetMapping(value = "")
    public String main() {
        return "main";
    }

    @Autowired
    MessageService messageService;

    @GetMapping(value = "/{id}")
    public String delete(@PathVariable("id") long id) {
        messageService.delete(id);
        return "main";
    }
}
