package ru.mmalakhova.nsu.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.mmalakhova.nsu.model.Todo;
import ru.mmalakhova.nsu.service.TodoService;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/rest")
public class TodoController {

    private final TodoService service;

    public TodoController(TodoService service) {
        this.service = service;
    }

    @GetMapping()
    public ResponseEntity<List<Todo>> getAllTodos(@RequestParam(required = false) String status) {
        List<Todo> todos = new ArrayList<>();
        if (status == null) {
            todos.addAll(service.getAllTodos());
        } else {
            switch (status) {
                case "active" -> todos.addAll(service.getAllActiveTodos());
                case "completed" -> todos.addAll(service.getAllCompletedTodos());
                default -> todos.addAll(service.getAllTodos());
            }
        }
        if (todos.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(todos, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Todo> getTodoById(@PathVariable("id") Integer id) {
        Optional<Todo> todoData = service.getTodoById(id);
        return todoData.map(todo ->
                new ResponseEntity<>(todo, HttpStatus.OK)).orElseGet(() ->
                new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping()
    public Todo createTodo(@RequestBody Todo todo, HttpServletRequest httpServletRequest) {
        todo.setUrl(httpServletRequest.getRequestURL().toString());
        return service.updateTodo(todo);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Todo> updateTodo(@PathVariable("id")Integer id,
                                           @RequestBody Todo todo) {
        Optional<Todo> todoData = service.getTodoById(id);
        if (todoData.isPresent()) {
            if (todo.getTitle() != null) {
                todoData.get().setTitle(todo.getTitle());
            }
            if (todo.getCompleted() != null) {
                todoData.get().setCompleted(todo.getCompleted());
            }
            if (todo.getOrder() != null) {
                todoData.get().setOrder(todo.getOrder());
            }
            service.updateTodo(todoData.get());
            return new ResponseEntity<>(todoData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping()
    public ResponseEntity<HttpStatus> deleteAllTodos() {
        service.deleteAllTodos();
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Todo> deleteTodoById(@PathVariable("id")Integer id){
        Optional<Todo> todoToDelete = service.getTodoById(id);
        service.deleteTodoById(id);
        return todoToDelete.map(todo ->
                new ResponseEntity<>(todo, HttpStatus.OK)).orElseGet(() ->
                new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}