package com.programmers.summerinternship.demo.service;

import com.programmers.summerinternship.demo.model.Todo;
import com.programmers.summerinternship.demo.model.TodoDto;
import com.programmers.summerinternship.demo.repository.TodoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public List<Todo> findAll(long offset, int limit) {
        if (offset < 0)
            offset = 0;
        if (limit < 1 || limit > 30)
            limit = 20;

        return todoRepository.selectAll(offset, limit);
    }
}
