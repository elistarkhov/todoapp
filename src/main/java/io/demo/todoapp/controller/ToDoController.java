package io.demo.todoapp.controller;

import io.demo.todoapp.entity.dto.ToDoItemCreateDto;
import io.demo.todoapp.entity.dto.ToDoItemDto;
import io.demo.todoapp.service.ToDoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.UUID;

@Controller
public class ToDoController {
    private final ToDoService toDoService;

    @Autowired
    public ToDoController(ToDoService toDoService) {
        this.toDoService = toDoService;
    }

    @GetMapping("/add-todo")
    public String showToDoCreateForm(@ModelAttribute("todo") ToDoItemCreateDto createDto) {
        return "add-todo";
    }

    @PostMapping("/save")
    public String saveToDo(@Valid @ModelAttribute("todo") ToDoItemCreateDto createDto, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "redirect:/add-todo?error=true";
        }
        toDoService.createTodo(createDto);
        return "redirect:/";
    }

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("todos", toDoService.getAllTodos());
        return "index";
    }

    @GetMapping("/edit/{id}")
    public String showToDoEditForm(@PathVariable("id") UUID id, Model model) {
        ToDoItemDto todo = toDoService.findTodoById(id);
        model.addAttribute("todo", todo);
        return "update-todo";
    }

    @PostMapping("/update/{id}")
    public String updateToDo(@PathVariable("id") UUID id, @Valid ToDoItemDto updateDto, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("todo", updateDto);
            return "update-todo";
        }
        toDoService.updateTodo(updateDto);
        return "redirect:/";
    }

    @GetMapping("/delete/{id}")
    public String deleteToDo(@PathVariable("id") UUID id, Model model) {
        toDoService.deleteTodo(id);
        return "redirect:/";
    }

}

