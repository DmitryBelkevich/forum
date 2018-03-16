package com.hard.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/topics")
public class TopicController {
    @GetMapping(value = "")
    public ModelAndView getAll() {
        ModelAndView modelAndView = new ModelAndView("topics/topics_list");

        return modelAndView;
    }

    @GetMapping(value = "/{id}")
    public ModelAndView getById(@PathVariable long id) {
        ModelAndView modelAndView = new ModelAndView("topics/topic");

        return modelAndView;
    }

    @GetMapping(value = "/new")
    public ModelAndView newTopic() {
        ModelAndView modelAndView = new ModelAndView("topics/topic_new");

        return modelAndView;
    }

    @PostMapping(value = "/save")
    public ModelAndView save(
            @RequestParam("title") String title,
            @RequestParam("text") String text
    ) {
        ModelAndView modelAndView = new ModelAndView("topics/topics_list");

        return modelAndView;
    }
}
