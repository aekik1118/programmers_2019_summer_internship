package com.programmers.summerinternship.demo.service;

import com.programmers.summerinternship.demo.model.Todo;
import com.programmers.summerinternship.demo.model.TodoDto;
import com.programmers.summerinternship.demo.repository.TodoRepository;
import org.springframework.stereotype.Service;

@Service
public class TodoService {

    private final TodoRepository todoRepository;

    public TodoService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    public Todo create(TodoDto todoDto) {
        Todo createTodo = new Todo(todoDto);
        Long newSeq = todoRepository.save(createTodo);
        return Todo.builder()
                .seq(newSeq)
                .title(createTodo.getTitle())
                .contents(createTodo.getContents())
                .endAt(createTodo.getEndAt())
                .priority(createTodo.getPriority())
                .build();
    }
}
