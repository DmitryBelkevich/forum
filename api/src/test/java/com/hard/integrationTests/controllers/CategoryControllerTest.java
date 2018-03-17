package com.hard.integrationTests.controllers;

import com.hard.config.AppConfig;
import com.hard.config.MvcConfig;
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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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
public class CategoryControllerTest {
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
                get("/api/categories")
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
        jdbcTemplate.execute("INSERT INTO categories (title) VALUES ('category2')");

        mockMvc.perform(
                get("/api/categories")
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
                .andExpect(jsonPath("$[0].title").value("category1"))
                .andExpect(jsonPath("$[1].id").value(2))
                .andExpect(jsonPath("$[1].title").value("category2"))
        ;
    }

    @Test
    public void getAll_shouldReturnCategoriesByRequestParamsAndReturnStatusOk200() throws Exception {
        jdbcTemplate.execute("INSERT INTO categories (title) VALUES ('category1')");
        jdbcTemplate.execute("INSERT INTO categories (title) VALUES ('category2')");

        mockMvc.perform(
                get("/api/categories?id=1&title=category1")
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
                .andExpect(jsonPath("$[0].title").value("category1"))
        ;
    }

    /**
     * getById
     */

    @Test
    public void getById_shouldReturnStatusNotFound404() throws Exception {
        mockMvc.perform(
                get("/api/categories/{id}", 1L)
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
        jdbcTemplate.execute("INSERT INTO categories (title) VALUES ('category2')");

        mockMvc.perform(
                get("/api/categories/{id}", 1L)
        )
                // status
                .andExpect(status().isOk())
                // headers
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(header().string(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN, "*"))
                .andExpect(header().string(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_UTF8_VALUE))
                // body
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.title").value("category1"))
        ;
    }
}
