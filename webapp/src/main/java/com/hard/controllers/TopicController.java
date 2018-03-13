package com.hard.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
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

        modelAndView.addObject("id", id);

        return modelAndView;
    }
}
