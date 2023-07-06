package org.aeros.tasks.taskmanager.service;

import java.util.List;
import lombok.AllArgsConstructor;
import org.aeros.tasks.taskmanager.domain.model.Task;
import org.aeros.tasks.taskmanager.domain.model.TaskList;
import org.aeros.tasks.taskmanager.repository.TaskRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@AllArgsConstructor
class DefaultTaskManagerService implements TaskManagerService {

    private final TaskRepository taskRepository;

    @Override
    public TaskList createList(String title) {
        return taskRepository.save(new TaskList(null, user(), title));
    }

    @Override
    public List<TaskList> getLists() {
        return taskRepository.findAll(user());
    }

    @Override
    public void updateList(TaskList list) {
        taskRepository.updateList(list);
    }

    @Override
    public void deleteAllLists() {
        taskRepository.deleteAll(user());
    }

    @Override
    public void deleteListById(Long listID) {
        taskRepository.deleteList(user(), listID);
    }

    @Override
    public Task addTask(Task task) {
        TaskList list = taskRepository.findListById(user(), task.id()).orElseThrow();
        return taskRepository.save(list.id(), task);
    }

    @Override
    public List<Task> getTasks(Long listID) {
        return taskRepository.findAllTasks(user(), listID);
    }

    @Override
    public void updateTask(Task task) {
        if (taskRepository.findListById(user(), task.listID()).isPresent()) {
            taskRepository.updateTask(task.listID(), task);
        }
    }

    @Override
    public void deleteTask(Long id) {
        taskRepository.deleteTask(user(), id);
    }

    @Override
    public void deleteList(Long id) {
        taskRepository.deleteList(user(), id);
    }

    @Override
    public void deleteAllTasks(Long id) {
        if (taskRepository.findListById(user(), id).isPresent()) taskRepository.deleteAllTasks(user(), id);
    }

    private String user() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || authentication.getPrincipal() == null) {
            return null;
        }

        return authentication.getName();
    }
}
