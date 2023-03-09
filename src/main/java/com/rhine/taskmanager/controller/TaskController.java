package com.rhine.taskmanager.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.rhine.taskmanager.domain.dto.TaskListResponse;
import com.rhine.taskmanager.domain.dto.TaskRequest;
import com.rhine.taskmanager.domain.dto.TaskResponse;
import com.rhine.taskmanager.service.TaskManagerService;

@RestController
@RequestMapping("/api")
public class TaskController {

    private TaskManagerService taskService;

    public TaskController(TaskManagerService taskService) {
        this.taskService = taskService;
    }

    @PostMapping("/list/create")
    @ResponseStatus(HttpStatus.CREATED)
    public TaskListResponse createNewList(@RequestParam String title) {
        return taskService.createList(title);
    }

    @GetMapping("/list")
    public List<TaskListResponse> getLists() {
        return taskService.getTaskLists();
    }

    @DeleteMapping("/list/delete")
    public void deleteList(@RequestParam Long id) {
        taskService.deleteListById(id);
    }

    @DeleteMapping("/list/clear")
    public void clearList() {
        taskService.deleteAllLists();
    }

    @PutMapping("/list/task")
    public ResponseEntity<TaskResponse> updateTask(@RequestParam Long taskId, @RequestBody TaskRequest taskRequest) {
        TaskResponse task = taskService.updateTask(taskId, taskRequest);
        if (task == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(task);
    }

    @GetMapping("/list/task")
    public List<TaskResponse> getTasks(@RequestParam Long listId) {
        return taskService.getTasksByList(listId);
    }

    @DeleteMapping("/list/task")
    public void deleteTask(@RequestParam Long listId, @RequestParam Long taskId) {
        taskService.deleteTasksFromList(listId, taskId);
    }

    @PostMapping("/list/task/new")
    @ResponseStatus(HttpStatus.CREATED)
    public Optional<TaskResponse> createTask(@RequestParam Long listId, @RequestBody TaskRequest task) {
        return taskService.createTaskForUser(listId, task);
    }

    @DeleteMapping("/list/task/clear")
    void clearTasks(@RequestParam Long listId) {
        taskService.clearAllTasksFromList(listId);
    }

}
