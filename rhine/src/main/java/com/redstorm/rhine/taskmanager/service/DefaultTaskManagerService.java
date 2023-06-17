package com.redstorm.rhine.taskmanager.service;

import com.redstorm.rhine.taskmanager.domain.model.Task;
import com.redstorm.rhine.taskmanager.domain.model.TaskList;
import com.redstorm.rhine.taskmanager.repository.TaskRepository;

import lombok.AllArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@AllArgsConstructor
class DefaultTaskManagerService implements TaskManagerService {

    private final TaskRepository taskRepository;

    @Override
    public TaskList createList(String title) {
        return null;
    }

    @Override
    public Task addTask(Task task) {
        return null;
    }

    @Override
    public Task updateTask(Long id, Task task, Long userID) {
        return null;
    }

    @Override
    public void deleteTask(Long id) {}

    @Override
    public void deleteList(Long id) {}

    @Override
    public void deleteAllTasks(Long id) {}
}
