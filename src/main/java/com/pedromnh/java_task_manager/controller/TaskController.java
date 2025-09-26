package com.pedromnh.java_task_manager.controller;

import com.pedromnh.java_task_manager.model.Status;
import com.pedromnh.java_task_manager.model.Task;
import com.pedromnh.java_task_manager.service.TaskService;
import org.springframework.http.ResponseEntity;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.annotation.Validated;

import org.springframework.format.annotation.DateTimeFormat;
import jakarta.validation.constraints.FutureOrPresent;
import java.time.LocalDate;
import java.util.List;

@Validated
@RestController
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService service;

    public TaskController(TaskService taskService) {
        this.service = taskService;
    }

    @GetMapping
    public List<Task> getAllTasks(
            @RequestParam(required = false) Status status,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @FutureOrPresent LocalDate dueBefore) {
        return service.getAll(status, dueBefore != null ? LocalDate.parse(dueBefore.toString()) : null);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Task> getTaskById(@Valid @PathVariable Long id) {
        return service.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Task createTask(@Valid @RequestBody Task task) {
        return service.create(task);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Task> updateTask(@PathVariable Long id, @Valid @RequestBody Task taskDetails) {
        return service.update(id, taskDetails)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@Valid @PathVariable Long id) {
        if (service.delete(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

}
