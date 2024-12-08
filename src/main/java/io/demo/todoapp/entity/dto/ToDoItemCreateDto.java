package io.demo.todoapp.entity.dto;

import jakarta.validation.constraints.NotBlank;

import java.io.Serializable;
import java.util.Objects;

public class ToDoItemCreateDto implements Serializable {
    @NotBlank(message = "Title is required")
    private final String title;
    private final String content;
    private final boolean completed;

    public ToDoItemCreateDto(String title, String content) {
        this.title = title;
        this.content = content;
        this.completed = false;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public boolean getCompleted() {
        return completed;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ToDoItemCreateDto entity = (ToDoItemCreateDto) o;
        return Objects.equals(this.title, entity.title) &&
                Objects.equals(this.content, entity.content) &&
                Objects.equals(this.completed, entity.completed);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, content, completed);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "title = " + title + ", " +
                "content = " + content + ", " +
                "completed = " + completed + ")";
    }
}