package org.aeros.tasks.manager.service;

import org.aeros.tasks.manager.domain.dto.TaskListResponse;
import org.aeros.tasks.manager.domain.dto.TaskRequest;
import org.aeros.tasks.manager.domain.dto.TaskResponse;
import org.aeros.tasks.manager.domain.model.TaskList;

import java.util.List;

public interface TaskManagerService {

    TaskList createList(String title);

    List<TaskListResponse> getLists();

    TaskListResponse getListById(Long id);

    void deleteList(Long id);

    void deleteAllLists();

    TaskResponse addTask(Long listId, TaskRequest task);

    List<TaskResponse> getTasksByList(Long id);

    void updateTask(Long id, TaskRequest task);

    void deleteTask(Long listId, Long taskId);

    void deleteAllTasks(Long id);
}
