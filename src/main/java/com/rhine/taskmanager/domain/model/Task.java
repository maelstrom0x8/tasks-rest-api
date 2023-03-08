package com.rhine.taskmanager.domain.model;

import java.time.LocalDateTime;

import com.rhine.redstorm.util.Constants;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
public class Task {

    @Id
    @GeneratedValue(generator = Constants.ID_GENERATOR)
    protected Long id;

    protected String name;
    protected String description;

    @Temporal(TemporalType.TIMESTAMP)
    protected LocalDateTime schedule;

    protected boolean completed;

    @ManyToOne
    @JoinColumn(name = "list_id", nullable = false)
    protected TaskList list;

    public Task() {}
    
    public Task(String name, String description, LocalDateTime schedule, boolean completed,
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
