package com.redstorm.rhine.taskmanager.service;

import com.redstorm.rhine.taskmanager.domain.model.Task;
import com.redstorm.rhine.taskmanager.domain.model.TaskList;

public interface TaskManagerService {

    TaskList createList(String title);

    Task addTask(Task task);

    Task updateTask(Long id, Task task, Long userID);

    void deleteTask(Long id);

    void deleteList(Long id);

    void deleteAllTasks(Long id);
}
