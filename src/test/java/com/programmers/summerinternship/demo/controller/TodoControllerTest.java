package com.programmers.summerinternship.demo.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.programmers.summerinternship.demo.model.TodoDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class TodoControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    public void createTodo() throws Exception {

        TodoDto todoDto = TodoDto.builder()
                .title("controller test title")
                .contents("controller test contents")
                .endAt(LocalDateTime.now().plusDays(1))
                .build();

        mockMvc.perform(post("/api/todo/")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(objectMapper.writeValueAsString(todoDto)))
                    .andDo(print())
                    .andExpect(status().isOk());

    }

    @Test
    public void createTodo_wrong() throws Exception {

        TodoDto todoDto = TodoDto.builder()
                .title("")
                .contents("controller test contents")
                .endAt(LocalDateTime.now().plusDays(1))
                .build();

        mockMvc.perform(post("/api/todo/")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(objectMapper.writeValueAsString(todoDto)))
                    .andDo(print())
                    .andExpect(status().isBadRequest());
    }

    @Test
    public void getTodoList() throws Exception {

        Long offset = 0L;
        Integer limit = 10;

        mockMvc.perform(get("/api/todo/list")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .param("offset", offset.toString())
                .param("limit", limit.toString()))
                    .andDo(print())
                    .andExpect(status().isOk());

    }

}