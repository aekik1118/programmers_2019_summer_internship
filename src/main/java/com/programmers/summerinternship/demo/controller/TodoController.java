package com.programmers.summerinternship.demo.controller;

import com.programmers.summerinternship.demo.model.Todo;
import com.programmers.summerinternship.demo.model.TodoDto;
import com.programmers.summerinternship.demo.service.TodoService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;
import java.util.Optional;

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

    @GetMapping(path = "list")
    public ResponseEntity getTodoList(Long offset, int limit){
        List<Todo> todoList = todoService.findAll(offset, limit);
        return ResponseEntity.ok(todoList);
    }

    @GetMapping("/{seq}")
    public ResponseEntity getTodo(@PathVariable Long seq){
        Optional<Todo> getTodo = todoService.getTodo(seq);
        if(getTodo.isPresent()){
            return ResponseEntity.ok(getTodo.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{seq}")
    public ResponseEntity updateTodo(@PathVariable Long seq, @RequestBody TodoDto todoDto){
        try {
            Optional<Todo> getTodo = todoService.getTodo(seq);
            if(!getTodo.isPresent()){
                return ResponseEntity.ok(getTodo.get());
            }

            Todo updateTodo = todoService.update(seq, todoDto);
            return ResponseEntity.ok(updateTodo);
        } catch (Exception e){
            return ResponseEntity.badRequest().body(e.toString());
        }
    }
}
