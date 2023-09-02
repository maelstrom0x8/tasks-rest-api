package org.aeros.tasks.manager.domain.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "tasks")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "task_id")
    private Long id;

    @Column(nullable = false)
    private String name;

    private String description;

    private LocalDateTime schedule;

    private boolean completed;

    @ManyToOne
    @JoinColumn(name = "list_id", nullable = false)
    private TaskList list;

    public Task() {}

    public Task(
            Long id, String name, String description, LocalDateTime schedule, boolean completed) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.schedule = schedule;
        this.completed = completed;
    }

    public Task(
            String name,
            String description,
            LocalDateTime schedule,
            boolean completed,
            TaskList list) {
        this.name = name;
        this.description = description;
        this.schedule = schedule;
        this.completed = completed;
        this.list = list;
    }

    public Long getId() {
        return id;
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
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public TaskList getList() {
        return list;
    }

    public void setList(TaskList list) {
        this.list = list;
    }
}
