package com.programmers.summerinternship.demo.service;

import com.programmers.summerinternship.demo.model.Todo;
import com.programmers.summerinternship.demo.model.TodoDto;
import com.programmers.summerinternship.demo.repository.TodoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TodoService {

    private final TodoRepository todoRepository;

    public TodoService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    public Todo create(TodoDto todoDto) {
        Todo createTodo = new Todo(todoDto);
        Long result = todoRepository.save(createTodo);
        return createTodo;
    }

    public List<Todo> findAll(long offset, int limit) {
        if (offset < 0)
            offset = 0;
        if (limit < 1 || limit > 30)
            limit = 20;

        return todoRepository.selectAll(offset, limit);
    }

    public Optional<Todo> getTodo(Long seq) {
        return todoRepository.select(seq);
    }

    public Todo update(Long seq, TodoDto todo) {
        Todo updateTodo = Todo.builder()
                .seq(seq)
                .title(todo.getTitle())
                .contents(todo.getContents())
                .deadline(todo.getDeadline())
                .hasDeadline(todo.isHasDeadline())
                .priority(todo.getPriority())
                .isDone(todo.isDone())
                .build();

        Long result =  todoRepository.update(updateTodo);
        return updateTodo;
    }

    public void delete(Long seq) {
        todoRepository.delete(seq);
    }

    public List<Todo> findDoneAll(long offset, int limit) {
        if (offset < 0)
            offset = 0;
        if (limit < 1 || limit > 30)
            limit = 20;

        return todoRepository.selectDoneAll(offset, limit);
    }
}
