package com.rhine.redstorm.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;

@Entity
public class Task {

    @Id
    @GeneratedValue
    @Column(nullable = false, unique = true)
    private Long id;

    @NotNull
    private String name;
    
    private String description;
    private LocalDateTime schedule;
    private boolean isCompleted;

    @ManyToOne
    @JoinColumn(name = "list_id", nullable = false)
    private TaskList list;

    public Task(Long id, @NotNull String name, String description, LocalDateTime schedule, boolean isCompleted,
            TaskList list) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.schedule = schedule;
        this.isCompleted = isCompleted;
        this.list = list;
    }

    public Task() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getSchedule() {
        return schedule;
    }

    public void setSchedule(LocalDateTime schedule) {
        this.schedule = schedule;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean isCompleted) {
        this.isCompleted = isCompleted;
    }

    public TaskList getList() {
        return list;
    }

    public void setList(TaskList list) {
        this.list = list;
    }
}
