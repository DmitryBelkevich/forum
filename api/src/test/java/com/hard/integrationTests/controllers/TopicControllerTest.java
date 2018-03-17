package com.hard.integrationTests.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hard.config.AppConfig;
import com.hard.config.MvcConfig;
import com.hard.models.Category;
import com.hard.models.Topic;
import com.hard.models.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
        classes = {
                AppConfig.class,
                MvcConfig.class,
        }
)
@WebAppConfiguration
@SqlGroup({
        @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = {
                "classpath:sql/00.sql",
                "classpath:sql/01.sql",
        }),
        @Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD, scripts = {
                "classpath:sql/00.sql",
        })
})
public class TopicControllerTest {
    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Before
    public void init() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    /**
     * getAll
     */

    @Test
    public void getAll_shouldReturnStatusNoContent204() throws Exception {
        mockMvc.perform(
                get("/api/topics")
        )
                // status
                .andExpect(status().isNoContent())
                // headers
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(header().string(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN, "*"))
                .andExpect(header().string(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_UTF8_VALUE))
                // body
                .andExpect(content().string(""))
        ;
    }

    @Test
    public void getAll_shouldReturnCategoriesAndReturnStatusOk200() throws Exception {
        jdbcTemplate.execute("INSERT INTO categories (title) VALUES ('category1')");
        jdbcTemplate.execute("INSERT INTO users (username, password) VALUES ('user1', '1234')");

        jdbcTemplate.execute("INSERT INTO topics (title, user_id, category_id) VALUES ('topic1', 1, 1)");
        jdbcTemplate.execute("INSERT INTO topics (title, user_id, category_id) VALUES ('topic2', 1, 1)");

        mockMvc.perform(
                get("/api/topics")
        )
                // status
                .andExpect(status().isOk())
                // headers
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(header().string(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN, "*"))
                .andExpect(header().string(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_UTF8_VALUE))
                // body
                .andExpect(jsonPath("$.size()").value(2))
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].title").value("topic1"))
                .andExpect(jsonPath("$[1].id").value(2))
                .andExpect(jsonPath("$[1].title").value("topic2"))
        ;
    }

    @Test
    public void getAll_shouldReturnCategoriesByRequestParamsAndReturnStatusOk200() throws Exception {
        jdbcTemplate.execute("INSERT INTO categories (title) VALUES ('category1')");
        jdbcTemplate.execute("INSERT INTO users (username, password) VALUES ('user1', '1234')");

        jdbcTemplate.execute("INSERT INTO topics (title, user_id, category_id) VALUES ('topic1', 1, 1)");
        jdbcTemplate.execute("INSERT INTO topics (title, user_id, category_id) VALUES ('topic2', 1, 1)");

        mockMvc.perform(
                get("/api/topics?id=1&title=topic1")
        )
                // status
                .andExpect(status().isOk())
                // headers
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(header().string(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN, "*"))
                .andExpect(header().string(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_UTF8_VALUE))
                // body
                .andExpect(jsonPath("$.size()").value(1))
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].title").value("topic1"))
        ;
    }

    /**
     * getById
     */

    @Test
    public void getById_shouldReturnStatusNotFound404() throws Exception {
        mockMvc.perform(
                get("/api/topics/{id}", 1L)
        )
                // status
                .andExpect(status().isNotFound())
                // headers
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(header().string(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN, "*"))
                .andExpect(header().string(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_UTF8_VALUE))
                // body
                .andExpect(content().string(""))
        ;
    }

    @Test
    public void getById_shouldReturnCategoryAndReturnStatusOk200() throws Exception {
        jdbcTemplate.execute("INSERT INTO categories (title) VALUES ('category1')");
        jdbcTemplate.execute("INSERT INTO users (username, password) VALUES ('user1', '1234')");

        jdbcTemplate.execute("INSERT INTO topics (title, user_id, category_id) VALUES ('topic1', 1, 1)");
        jdbcTemplate.execute("INSERT INTO topics (title, user_id, category_id) VALUES ('topic2', 1, 1)");

        mockMvc.perform(
                get("/api/topics/{id}", 1L)
        )
                // status
                .andExpect(status().isOk())
                // headers
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(header().string(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN, "*"))
                .andExpect(header().string(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_UTF8_VALUE))
                // body
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.title").value("topic1"))
        ;
    }

    /**
     * save
     */

    @Test
    public void save_shouldAddAndReturnStatusCreated201() throws Exception {
        jdbcTemplate.execute("INSERT INTO categories (title) VALUES ('category1')");
        jdbcTemplate.execute("INSERT INTO users (username, password) VALUES ('user1', '1234')");

        jdbcTemplate.execute("INSERT INTO topics (title, user_id, category_id) VALUES ('topic1', 1, 1)");

        Topic topic = new Topic();
        topic.setTitle("topic2");

        Category category = new Category();
        category.setId(1);
        topic.setCategory(category);

        User user = new User();
        user.setId(1);
        topic.setUser(user);

        String topicJson = new ObjectMapper().writeValueAsString(topic);

        mockMvc.perform(
                post("/api/topics")
                        .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                        .content(topicJson)
        )
                // status
                .andExpect(status().isCreated())
                // headers
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(header().string(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN, "*"))
                .andExpect(header().string(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_UTF8_VALUE))
                // body
                .andExpect(jsonPath("$.id").value(2))
                .andExpect(jsonPath("$.title").value("topic2"))
        ;

        mockMvc.perform(
                get("/api/topics")
        )
                // status
                .andExpect(status().isOk())
                // headers
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(header().string(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN, "*"))
                .andExpect(header().string(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_UTF8_VALUE))
                // body
                .andExpect(jsonPath("$.size()").value(2))
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].title").value("topic1"))
                .andExpect(jsonPath("$[1].id").value(2))
                .andExpect(jsonPath("$[1].title").value("topic2"))
        ;
    }

    @Test
    public void save_shouldUpdateAndReturnStatusOk200() throws Exception {
        jdbcTemplate.execute("INSERT INTO categories (title) VALUES ('category1')");
        jdbcTemplate.execute("INSERT INTO users (username, password) VALUES ('user1', '1234')");

        jdbcTemplate.execute("INSERT INTO topics (title, user_id, category_id) VALUES ('topic1', 1, 1)");

        Topic topic = new Topic();
        topic.setId(1);
        topic.setTitle("topic2");

        Category category = new Category();
        category.setId(1);
        topic.setCategory(category);

        User user = new User();
        user.setId(1);
        topic.setUser(user);

        String categoryJson = new ObjectMapper().writeValueAsString(topic);

        mockMvc.perform(
                post("/api/topics")
                        .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                        .content(categoryJson)
        )
                // status
                .andExpect(status().isOk())
                // headers
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(header().string(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN, "*"))
                .andExpect(header().string(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_UTF8_VALUE))
                // body
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.title").value("topic2"))
        ;

        mockMvc.perform(
                get("/api/topics")
        )
                // status
                .andExpect(status().isOk())
                // headers
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(header().string(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN, "*"))
                .andExpect(header().string(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_UTF8_VALUE))
                // body
                .andExpect(jsonPath("$.size()").value(1))
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].title").value("topic2"))
        ;
    }

    /**
     * delete
     */

    @Test
    public void delete_shouldReturnStatusNotFound404() throws Exception {
        jdbcTemplate.execute("INSERT INTO categories (title) VALUES ('category1')");
        jdbcTemplate.execute("INSERT INTO users (username, password) VALUES ('user1', '1234')");

        jdbcTemplate.execute("INSERT INTO topics (title, user_id, category_id) VALUES ('topic1', 1, 1)");
        jdbcTemplate.execute("INSERT INTO topics (title, user_id, category_id) VALUES ('topic2', 1, 1)");

        mockMvc.perform(
                delete("/api/topics/{id}", 3L)
        )
                // status
                .andExpect(status().isNotFound())
                // headers
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(header().string(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN, "*"))
                .andExpect(header().string(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_UTF8_VALUE))
                // body
                .andExpect(content().string(""))
        ;

        mockMvc.perform(
                get("/api/topics")
        )
                // status
                .andExpect(status().isOk())
                // headers
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(header().string(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN, "*"))
                .andExpect(header().string(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_UTF8_VALUE))
                // body
                .andExpect(jsonPath("$.size()").value(2))
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].title").value("topic1"))
                .andExpect(jsonPath("$[1].id").value(2))
                .andExpect(jsonPath("$[1].title").value("topic2"))
        ;
    }

    @Test
    public void delete_shouldDeleteAndReturnStatusNoContent204() throws Exception {
        jdbcTemplate.execute("INSERT INTO categories (title) VALUES ('category1')");
        jdbcTemplate.execute("INSERT INTO users (username, password) VALUES ('user1', '1234')");

        jdbcTemplate.execute("INSERT INTO topics (title, user_id, category_id) VALUES ('topic1', 1, 1)");
        jdbcTemplate.execute("INSERT INTO topics (title, user_id, category_id) VALUES ('topic2', 1, 1)");

        mockMvc.perform(
                delete("/api/topics/{id}", 1L)
        )
                // status
                .andExpect(status().isNoContent())
                // headers
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(header().string(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN, "*"))
                .andExpect(header().string(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_UTF8_VALUE))
                // body
                .andExpect(content().string(""))
        ;

        mockMvc.perform(
                get("/api/topics")
        )
                // status
                .andExpect(status().isOk())
                // headers
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(header().string(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN, "*"))
                .andExpect(header().string(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_UTF8_VALUE))
                // body
                .andExpect(jsonPath("$.size()").value(1))
                .andExpect(jsonPath("$[0].id").value(2))
                .andExpect(jsonPath("$[0].title").value("topic2"))
        ;
    }

    /**
     * deleteAll
     */

    @Test
    public void delete_shouldDeleteAllAndReturnStatusNoContent204() throws Exception {
        jdbcTemplate.execute("INSERT INTO categories (title) VALUES ('category1')");
        jdbcTemplate.execute("INSERT INTO users (username, password) VALUES ('user1', '1234')");

        jdbcTemplate.execute("INSERT INTO topics (title, user_id, category_id) VALUES ('topic1', 1, 1)");
        jdbcTemplate.execute("INSERT INTO topics (title, user_id, category_id) VALUES ('topic2', 1, 1)");

        mockMvc.perform(
                delete("/api/topics")
        )
                // status
                .andExpect(status().isNoContent())
                // headers
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(header().string(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN, "*"))
                .andExpect(header().string(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_UTF8_VALUE))
                // body
                .andExpect(content().string(""))
        ;

        mockMvc.perform(
                get("/api/topics")
        )
                // status
                .andExpect(status().isNoContent())
                // headers
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(header().string(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN, "*"))
                .andExpect(header().string(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_UTF8_VALUE))
                // body
                .andExpect(content().string(""))
        ;
    }
}
