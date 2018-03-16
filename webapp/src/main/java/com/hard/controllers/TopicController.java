package com.hard.controllers;

import com.hard.models.Message;
import com.hard.models.Topic;
import com.hard.services.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Collection;

@Controller
@RequestMapping("/topics")
public class TopicController {
    @Autowired
    private TopicService topicService;

    @GetMapping(value = "")
    public ModelAndView getAll() {
        ModelAndView modelAndView = new ModelAndView("topics/topics_list");

        return modelAndView;
    }

    @GetMapping(value = "/{id}")
    public ModelAndView getById(@PathVariable long id) {
        ModelAndView modelAndView = new ModelAndView("topics/topic");

        Topic topic = topicService.getById(id);

        Collection<Message> messages = topic.getMessages();

        modelAndView.addObject("messages", messages);

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
