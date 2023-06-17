package com.redstorm.rhine.taskmanager.repository;

import com.redstorm.rhine.taskmanager.domain.model.Task;
import com.redstorm.rhine.taskmanager.domain.model.TaskList;

import java.util.List;
import java.util.Optional;

public interface TaskRepository {

    TaskList save(TaskList list);

    Task save(Long listID, Task task);

    List<TaskList> findAll(String owner);

    Optional<TaskList> findByOwner(String owner);

    void deleteAll(String owner);

    void deleteTask(String owner, Long listID, Long taskID);

    void deleteAllTasks(String owner, Long listID);
}
