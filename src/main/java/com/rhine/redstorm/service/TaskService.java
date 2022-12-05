package com.rhine.redstorm.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rhine.redstorm.domain.Task;
import com.rhine.redstorm.domain.TaskList;
import com.rhine.redstorm.repository.TaskListRepository;

@Service
public class TaskService implements ITaskService {

    @Autowired
    TaskListRepository taskListRepository;

    @Override
    public TaskList createList(String name) {
        TaskList list = new TaskList();
        list.setName(name);
        return taskListRepository.save(list);
    }

    @Override
    public void deleteList(Long id) {
        taskListRepository.deleteById(id);
    }

    @Override
    public void renameList(Long id, String name) {
        
    }

    @Override
    public void addTask(Long list_id, Task task) {
        Optional<TaskList> list = taskListRepository.findById(list_id);
        
        if(list.isPresent()) {
            list.get().getTasks().add(task);
            task.setList(list.get());

            taskListRepository.save(list.get());
        }
    }

    @Override
    public void removeTask(Long id) {
        taskListRepository.deleteById(id);
    }

    @Override
    public void clearList(Long id) {

    }

    @Override
    public void removeTasks(List<Long> ids) {
        taskListRepository.deleteAllByIdInBatch(ids);
    }

    @Override
    public void updateTask(Long id, Task task) {
        
    }
    
}
