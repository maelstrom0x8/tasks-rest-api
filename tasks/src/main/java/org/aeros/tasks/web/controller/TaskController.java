package org.aeros.tasks.web.controller;

import java.util.List;
import lombok.AllArgsConstructor;
import org.aeros.tasks.taskmanager.domain.model.Task;
import org.aeros.tasks.taskmanager.domain.model.TaskList;
import org.aeros.tasks.taskmanager.service.TaskManagerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping(value = "/api/lists", produces = MediaType.APPLICATION_JSON_VALUE)
public class TaskController {

    private final TaskManagerService taskManagerService;

    @GetMapping
    public List<TaskList> getList() {
        return List.of(new TaskList(323L, "Daily", "bob"));
    }

    @GetMapping("/all")
    List<TaskList> getAllList() {
        return taskManagerService.getLists();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TaskList createList(@RequestParam String title) {
        return taskManagerService.createList(title);
    }

    @PutMapping("/update")
    public void update(TaskList list) {
        taskManagerService.updateList(list);
    }

    @DeleteMapping("/delete")
    public void deleteList(@RequestParam Long id) {
        taskManagerService.deleteListById(id);
    }

    @DeleteMapping("/delete/all")
    public void clearAllLists() {
        taskManagerService.deleteAllLists();
    }

    @PostMapping("/tasks")
    public Task addNewTask(@RequestBody Task task) {
        return taskManagerService.addTask(task);
    }

    @GetMapping("/tasks")
    public List<Task> getAllTasks(@RequestParam Long listID) {
        return taskManagerService.getTasks(listID);
    }

    @PutMapping("/tasks/update")
    public void update(@RequestBody Task task) {
        taskManagerService.updateTask(task);
    }

    @DeleteMapping("/tasks/delete")
    public void deleteTask(@RequestParam Long taskID) {
        taskManagerService.deleteTask(taskID);
    }

    @DeleteMapping("/tasks/delete/all")
    public void clearTasks(@RequestParam Long listID) {
        taskManagerService.deleteAllTasks(listID);
    }
}
