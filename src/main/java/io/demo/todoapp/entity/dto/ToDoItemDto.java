package io.demo.todoapp.entity.dto;

import jakarta.validation.constraints.NotBlank;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;

public class ToDoItemDto implements Serializable {
    private final UUID id;
    private final Date createdAt;
    private final Date updatedAt;
    @NotBlank(message = "Title is required")
    private final String title;
    private final String content;
    private final boolean completed;

    public ToDoItemDto(UUID id, Date createdAt, Date updatedAt, String title, String content, boolean completed) {
        this.id = id;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.title = title;
        this.content = content;
        this.completed = completed;
    }

    public UUID getId() {
        return id;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
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
        ToDoItemDto entity = (ToDoItemDto) o;
        return Objects.equals(this.id, entity.id) &&
                Objects.equals(this.createdAt, entity.createdAt) &&
                Objects.equals(this.updatedAt, entity.updatedAt) &&
                Objects.equals(this.title, entity.title) &&
                Objects.equals(this.content, entity.content) &&
                Objects.equals(this.completed, entity.completed);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, createdAt, updatedAt, title, content, completed);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "createdAt = " + createdAt + ", " +
                "updatedAt = " + updatedAt + ", " +
                "title = " + title + ", " +
                "content = " + content + ", " +
                "completed = " + completed + ")";
    }
}