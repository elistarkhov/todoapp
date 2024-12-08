package io.demo.todoapp.service;

import io.demo.todoapp.entity.ToDoItem;
import io.demo.todoapp.entity.dto.ToDoItemCreateDto;
import io.demo.todoapp.entity.dto.ToDoItemDto;
import io.demo.todoapp.repository.ToDoItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class ToDoService {

    private final ToDoItemRepository toDoItemRepository;

    @Autowired
    public ToDoService(ToDoItemRepository toDoItemRepository) {
        this.toDoItemRepository = toDoItemRepository;
    }


    public List<ToDoItemDto> getAllTodos() {
        List<ToDoItem> toDoItems = toDoItemRepository.findAll();
        List<ToDoItemDto> toDoItemDtoList = new ArrayList<>();
        for (ToDoItem toDoItem : toDoItems) {
            ToDoItemDto toDoItemDto = mapToDto(toDoItem);
            toDoItemDtoList.add(toDoItemDto);
        }
        return toDoItemDtoList;
    }

    public ToDoItem createTodo(ToDoItemCreateDto createDto) {
        ToDoItem toDoItem = new ToDoItem(createDto.getTitle());
        toDoItem.setContent(createDto.getContent());
        return toDoItemRepository.save(toDoItem);
    }

    public ToDoItemDto findTodoById(UUID id) {
        ToDoItem toDoItem = toDoItemRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("todoItem with id: " + id + " not found"));
        return mapToDto(toDoItem);
    }

    public void updateTodo(ToDoItemDto toDoItemDto) {
        ToDoItem updatedTodo = toDoItemRepository.findById(toDoItemDto.getId())
                .orElseThrow(() -> new IllegalArgumentException("todoItem with id: " + toDoItemDto.getId() + " not found"));
        updatedTodo.setUpdatedAt(new Date());
        updatedTodo.setTitle(toDoItemDto.getTitle());
        updatedTodo.setContent(toDoItemDto.getContent());
        updatedTodo.setCompleted(toDoItemDto.getCompleted());
        toDoItemRepository.save(updatedTodo);
    }

    public void deleteTodo(UUID id) {
//        ToDoItem toDoItem = findTodoById(id);
        toDoItemRepository.deleteById(id);
    }

    private ToDoItemDto mapToDto(ToDoItem toDoItem) {
        return new ToDoItemDto(toDoItem.getId(),
                toDoItem.getCreatedAt(),
                toDoItem.getUpdatedAt(),
                toDoItem.getTitle(),
                toDoItem.getContent(),
                toDoItem.isCompleted());
    }
}
