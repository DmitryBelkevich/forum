package com.hard.controllers;

import com.hard.models.Message;
import com.hard.models.Topic;
import com.hard.services.CategoryService;
import com.hard.services.TopicService;
import com.hard.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

@Controller
@RequestMapping("/topics")
public class TopicController {
    @Autowired
    private TopicService topicService;

    @GetMapping("")
    public ModelAndView getAll() {
        ModelAndView modelAndView = new ModelAndView("topics/topics_list");

        Collection<Topic> topics = topicService.getAll(null);

        modelAndView.addObject("topics", topics);

        return modelAndView;
    }

    @GetMapping("/{id}")
    public ModelAndView getById(@PathVariable long id) {
        ModelAndView modelAndView = new ModelAndView("topics/topic");

        Topic topic = topicService.getById(id);

        Collection<Message> messages = topic.getMessages();

        modelAndView.addObject("messages", messages);

        return modelAndView;
    }

    @GetMapping("/new")
    public ModelAndView newTopic() {
        ModelAndView modelAndView = new ModelAndView("topics/topic_new");

        modelAndView.addObject("command", new Topic());

        return modelAndView;
    }

    @Autowired
    private UserService userService;

    @Autowired
    private CategoryService categoryService;

    @PostMapping("/save")
    public ModelAndView save(
            @ModelAttribute Topic topic,
            @RequestParam("text") String text
    ) {
        ModelAndView modelAndView = new ModelAndView("topics/topics_list");

        // 1 формирование Topic.Title

        // 2 формирование Topic.User
        topic.setUser(userService.getById(1));

        // 3 формирование Topic.Collection<Message>
        Message message = new Message();

        // 1 text
        message.setText(text);
        // 2 topic
//            message.setTopic(topic);
        // 3 user
//            message.setUser(userService.getById(1));
        // 4 date
        message.setDate(new Date());

        Collection<Message> messages = new ArrayList<>();
        messages.add(message);
        topic.setMessages(messages);

        // 4 формирование Category
        topic.setCategory(categoryService.getById(1));

//        topicService.save(topic); // Exception

        return modelAndView;
    }
}
