package com.rhine.taskmanager.domain.dto;

public class TaskListResponse {
    
    private Long id;
    private String title;
    private String owner;
    
    
    public TaskListResponse() {}

    public TaskListResponse(Long id, String title, String owner) {
        this.id = id;
        this.title = title;
        this.owner = owner;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

}
