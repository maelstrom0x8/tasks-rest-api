package org.aeros.tasks.web.controller;

import org.aeros.tasks.manager.domain.dto.TaskListResponse;
import org.aeros.tasks.manager.domain.dto.TaskRequest;
import org.aeros.tasks.manager.domain.dto.TaskResponse;
import org.aeros.tasks.manager.domain.model.TaskList;
import org.aeros.tasks.manager.service.TaskManagerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/lists")
public class TaskController {

    private final TaskManagerService taskManagerService;

    public TaskController(TaskManagerService taskManagerService) {
        this.taskManagerService = taskManagerService;
    }

    @GetMapping
    public List<TaskListResponse> fetchLists() {
        return taskManagerService.getLists();
    }

    @GetMapping("/{id}")
    TaskListResponse fetchSingleList(@PathVariable Long id) {
        return taskManagerService.getListById(id);
    }

    @PostMapping
    public TaskListResponse postList(@RequestBody String title) {
        TaskList list = taskManagerService.createList(title);
        return new TaskListResponse(list.getId(), list.getTitle());
    }

    @DeleteMapping("/{id}")
    public void deleteList(@PathVariable Long id) {
        taskManagerService.deleteList(id);
    }

    @DeleteMapping("/all")
    public void clearList() {
        taskManagerService.deleteAllLists();
    }

    @PutMapping("/{id}/tasks")
    public ResponseEntity<Void> updateTask(
            @PathVariable Long listId, @RequestBody TaskRequest taskRequest) {
        taskManagerService.addTask(listId, taskRequest);
        return ResponseEntity.accepted().build();
    }

    @GetMapping("/{id}/tasks")
    public List<TaskResponse> getTasks(@PathVariable Long id) {
        return taskManagerService.getTasksByList(id);
    }

    @DeleteMapping("/{id}/tasks/{taskId}")
    public void deleteTask(@RequestParam Long id, @RequestParam Long taskId) {
        taskManagerService.deleteTask(id, taskId);
    }

    @PostMapping("/{id}/tasks")
    @ResponseStatus(HttpStatus.CREATED)
    public TaskResponse createTask(@RequestParam Long id, @RequestBody TaskRequest task) {
        return taskManagerService.addTask(id, task);
    }

    @DeleteMapping("/{id}/tasks/clear")
    void clearTasks(@RequestParam Long listId) {
        taskManagerService.deleteAllTasks(listId);
    }
}
