package com.programmers.summerinternship.demo.controller;

import com.programmers.summerinternship.demo.model.Todo;
import com.programmers.summerinternship.demo.model.TodoDto;
import com.programmers.summerinternship.demo.service.TodoService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.HttpClientErrorException;

import static org.springframework.http.ResponseEntity.badRequest;

@Controller
@RequestMapping(value = "/api/todo")
public class TodoController {

    private final TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @PostMapping
    public ResponseEntity createTodo(@RequestBody TodoDto todoDto){
        try {
            Todo createTodo = todoService.create(todoDto);
            return ResponseEntity.ok(createTodo);
        } catch (Exception e){
            return ResponseEntity.badRequest().body(e.toString());
        }
    }
}