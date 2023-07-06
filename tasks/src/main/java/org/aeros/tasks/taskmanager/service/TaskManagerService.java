package org.aeros.tasks.taskmanager.service;

import java.util.List;
import org.aeros.tasks.taskmanager.domain.model.Task;
import org.aeros.tasks.taskmanager.domain.model.TaskList;

public interface TaskManagerService {

    TaskList createList(String title);

    List<TaskList> getLists();

    void updateList(TaskList list);

    void deleteAllLists();

    void deleteListById(Long listID);

    Task addTask(Task task);

    List<Task> getTasks(Long listID);

    void updateTask(Task task);

    void deleteTask(Long id);

    void deleteList(Long id);

    void deleteAllTasks(Long id);
}
