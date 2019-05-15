package com.programmers.summerinternship.demo.service;

import com.programmers.summerinternship.demo.model.Todo;
import com.programmers.summerinternship.demo.model.TodoDto;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TodoServiceTest {

    @Autowired
    private TodoService todoService;

    @Test
    public void createTodo(){

        TodoDto todoDto = TodoDto.builder()
                .title("test create title")
                .contents("test create contents")
                .endAt(LocalDateTime.now().plusDays(1))
                .priority(1L)
                .build();

        Todo createdTodo = todoService.create(todoDto);

        assertThat(createdTodo.getTitle()).isEqualTo(todoDto.getTitle());
    }

    @Test(expected = IllegalArgumentException.class)
    public void createTodo_wrong_title(){

        TodoDto todoDto = TodoDto.builder()
                .title("")
                .contents("test create contents")
                .endAt(LocalDateTime.now().plusDays(1))
                .priority(1L)
                .build();

        Todo createdTodo = todoService.create(todoDto);
    }

    @Test(expected = IllegalArgumentException.class)
    public void createTodo_wrong_contents(){

        String overflowContents = RandomStringUtils.random(2001);

        TodoDto todoDto = TodoDto.builder()
                .title("test")
                .contents(overflowContents)
                .endAt(LocalDateTime.now().plusDays(1))
                .priority(1L)
                .build();

        Todo createdTodo = todoService.create(todoDto);
    }

    @Test(expected = IllegalArgumentException.class)
    public void createTodo_wrong_endAt(){

        String overflowContents = RandomStringUtils.random(2001);

        TodoDto todoDto = TodoDto.builder()
                .title("test")
                .contents("test contents")
                .endAt(LocalDateTime.now().minusDays(1))
                .priority(1L)
                .build();

        Todo createdTodo = todoService.create(todoDto);
    }

}