package io.demo.todoapp.repository;

import io.demo.todoapp.entity.ToDoItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ToDoItemRepository extends JpaRepository<ToDoItem, UUID> {
}