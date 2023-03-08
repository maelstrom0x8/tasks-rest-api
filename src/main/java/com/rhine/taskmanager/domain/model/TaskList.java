package com.rhine.taskmanager.domain.model;

import java.util.ArrayList;
import java.util.List;

import com.rhine.redstorm.util.Constants;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "list")
public class TaskList {

    @Id
    @GeneratedValue(generator = Constants.ID_GENERATOR)
    protected Long id;

    @Column(nullable = false, unique = true)
    protected String title;

    protected String owner;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "list", cascade = CascadeType.REMOVE)
    protected List<Task> tasks = new ArrayList<>();

    public TaskList() {}

    public TaskList(String title, String owner) {
        this.title = title;
        this.owner = owner;
    }

    public Long getId() {
        return id;
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

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

}
