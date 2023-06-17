package com.redstorm.rhine.web.controller;

import com.redstorm.rhine.taskmanager.service.TaskManagerService;

import lombok.AllArgsConstructor;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/api")
public class TaskController {

    private final TaskManagerService taskManagerService;

    //    public TaskController(TaskManagerService taskService) {
    //        this.taskService = taskService;
    //    }
    //
    //    @PostMapping("/list/create")
    //    @ResponseStatus(HttpStatus.CREATED)
    //    public TaskListResponse createNewList(@RequestParam String title) {
    //        return taskService.createList(title);
    //    }
    //
    //    @GetMapping("/list")
    //    public List<TaskListResponse> getLists() {
    //        return taskService.getTaskLists();
    //    }
    //
    //    @DeleteMapping("/list/delete")
    //    public void deleteList(@RequestParam Long id) {
    //        taskService.deleteListById(id);
    //    }
    //
    //    @DeleteMapping("/list/clear")
    //    public void clearList() {
    //        taskService.deleteAllLists();
    //    }
    //
    //    @PutMapping("/list/task")
    //    public ResponseEntity<TaskResponse> updateTask(@RequestParam Long taskId, @RequestBody
    // TaskRequest
    // taskRequest) {
    //        TaskResponse task = taskService.updateTask(taskId, taskRequest);
    //        if (task == null) {
    //            return ResponseEntity.notFound().build();
    //        }
    //        return ResponseEntity.ok(task);
    //    }
    //
    //    @GetMapping("/list/task")
    //    public List<TaskResponse> getTasks(@RequestParam Long listId) {
    //        return taskService.getTasksByList(listId);
    //    }
    //
    //    @DeleteMapping("/list/task")
    //    public void deleteTask(@RequestParam Long listId, @RequestParam Long taskId) {
    //        taskService.deleteTasksFromList(listId, taskId);
    //    }
    //
    //    @PostMapping("/list/task/new")
    //    @ResponseStatus(HttpStatus.CREATED)
    //    public Optional<TaskResponse> createTask(@RequestParam Long listId, @RequestBody
    // TaskRequest task) {
    //        return taskService.createTaskForUser(listId, task);
    //    }
    //
    //    @DeleteMapping("/list/task/clear")
    //    void clearTasks(@RequestParam Long listId) {
    //        taskService.clearAllTasksFromList(listId);
    //    }

}
