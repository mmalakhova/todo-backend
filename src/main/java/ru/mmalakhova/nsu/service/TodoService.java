package ru.mmalakhova.nsu.service;

import org.springframework.stereotype.Service;
import ru.mmalakhova.nsu.model.Todo;
import ru.mmalakhova.nsu.repository.TodoRepository;

import java.util.List;
import java.util.Optional;

@Service
public class TodoService {

    private final TodoRepository repository;

    public TodoService(TodoRepository repository) {
        this.repository = repository;
    }

    public List<Todo> getAllTodos() {
        return repository.findAll();
    }

    public List<Todo> getAllActiveTodos() {
        return repository.getAllByCompletedFalse();
    }

    public List<Todo> getAllCompletedTodos() {
        return repository.getAllByCompletedTrue();
    }

    public Optional<Todo> getTodoById(Integer id) {
        return repository.findById(id);
    }

    public Todo updateTodo(Todo todo) {
        return repository.save(todo);
    }

    public void deleteTodoById(Integer id) {
        repository.deleteById(id);
    }

    public void deleteAllTodos() {
        repository.deleteAll();
    }
}
