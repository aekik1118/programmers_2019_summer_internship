package com.programmers.summerinternship.demo.service;

import com.programmers.summerinternship.demo.model.Todo;
import com.programmers.summerinternship.demo.model.TodoDto;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.annotation.Order;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

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
                .priority(4L)
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

        TodoDto todoDto = TodoDto.builder()
                .title("test")
                .contents("test contents")
                .endAt(LocalDateTime.now().minusDays(1))
                .priority(1L)
                .build();

        Todo createdTodo = todoService.create(todoDto);
    }

    @Test
    public void getTodoList(){
        IntStream.range(0,30).forEach(this::generateTodo);

        List<Todo> todoList = todoService.findAll(2,10);
        assertThat(todoList.size()).isEqualTo(10);
    }

    @Test
    public void getTodo(){
        TodoDto todoDto = TodoDto.builder()
                .title("test get title")
                .contents("test get contents")
                .endAt(LocalDateTime.now().plusDays(1))
                .priority(4L)
                .build();

        Todo createdTodo = todoService.create(todoDto);
        Optional<Todo> getTodo = todoService.getTodo(createdTodo.getSeq());

        assertThat(getTodo.get().getContents()).isEqualTo(createdTodo.getContents());
    }

    @Test
    public void updateTodo(){
        Todo createTodo = generateTodo(1);

        TodoDto updateTodoDto = TodoDto.builder()
                .title("update title")
                .contents("update contents")
                .endAt(LocalDateTime.now().plusDays(21))
                .priority(10L)
                .isDone(true)
                .build();

        Todo update = todoService.update(createTodo.getSeq(), updateTodoDto);
        Optional<Todo> getTodo = todoService.getTodo(createTodo.getSeq());
        assertThat(update.getContents()).isEqualTo(getTodo.get().getContents());
    }

    private Todo generateTodo(int index){
        TodoDto todoDto = TodoDto.builder()
                .title("title " + index)
                .contents("test contents")
                .endAt(LocalDateTime.now().plusDays(index + 1))
                .priority(4L)
                .build();

        return todoService.create(todoDto);
    }

}