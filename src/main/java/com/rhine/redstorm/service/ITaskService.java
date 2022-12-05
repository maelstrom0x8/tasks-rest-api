package com.rhine.redstorm.service;

import java.util.List;

import com.rhine.redstorm.domain.Task;
import com.rhine.redstorm.domain.TaskList;

public interface ITaskService {
    
    public TaskList createList(String name);

    public void deleteList(Long id);

    public void renameList(Long id, String name);

    public void addTask(Long list_id, Task task);

    public void removeTask(Long id);

    public void clearList(Long id);

    public void removeTasks(List<Long> ids);

    public void updateTask(Long id, Task task);
}
