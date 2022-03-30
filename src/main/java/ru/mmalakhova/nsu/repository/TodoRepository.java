package ru.mmalakhova.nsu.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import ru.mmalakhova.nsu.model.Todo;

import java.util.List;

public interface TodoRepository extends JpaRepository<Todo, Integer> {
    List<Todo> getAllByCompletedFalse();
    List<Todo> getAllByCompletedTrue();
}
