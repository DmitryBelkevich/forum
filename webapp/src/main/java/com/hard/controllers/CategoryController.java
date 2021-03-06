package com.hard.controllers;

import com.hard.models.Category;
import com.hard.models.Topic;
import com.hard.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Collection;

@Controller
@RequestMapping("/categories")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping("")
    public ModelAndView getAll() {
        ModelAndView modelAndView = new ModelAndView("categories/categories_list");

        Collection<Category> categories = categoryService.getAll(null);

        modelAndView.addObject("categories", categories);

        return modelAndView;
    }

    @GetMapping("/{id}")
    public ModelAndView getById(@PathVariable long id) {
        ModelAndView modelAndView = new ModelAndView("topics/topics_list");

        Category category = categoryService.getById(id);

        Collection<Topic> topics = category.getTopics();

        modelAndView.addObject("topics", topics);

        return modelAndView;
    }
}
