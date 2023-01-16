package com.rhine.redstorm.dto;

import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

public class TaskInDto {

    private String name;
    private String description;
    @DateTimeFormat(iso = ISO.DATE_TIME)
    private LocalDateTime schedule;
    private boolean isCompleted;

    public TaskInDto(Long id, String name, String description, LocalDateTime schedule, boolean isCompleted) {
        
        this.name = name;
        this.description = description;
        this.schedule = schedule;
        this.isCompleted = isCompleted;
    }

    public TaskInDto() {
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

}
