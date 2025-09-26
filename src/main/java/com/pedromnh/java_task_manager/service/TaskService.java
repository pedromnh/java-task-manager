package com.pedromnh.java_task_manager.service;

import com.pedromnh.java_task_manager.model.Status;
import com.pedromnh.java_task_manager.model.Task;
import com.pedromnh.java_task_manager.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class TaskService {
    private final TaskRepository repository;

    public TaskService(TaskRepository repository) {
        this.repository = repository;
    }

    public List<Task> getAll(Status status, LocalDate dueBefore) {
        if (status != null && dueBefore != null) {
            return repository.findByStatusAndDueDateBefore(status, dueBefore);
        } else if (status != null) {
            return repository.findByStatus(status);
        } else if (dueBefore != null) {
            return repository.findByDueDateBefore(dueBefore);
        }
        return repository.findAll();
    }

    public Optional<Task> getById(Long id) {
        return repository.findById(id);
    }

    public Task create(Task task) {
        return repository.save(task);
    }

    public Optional<Task> update(Long id, Task taskDetails) {
        return repository.findById(id).map(task -> {
            task.setTitle(taskDetails.getTitle());
            task.setDescription(taskDetails.getDescription());
            task.setStatus(taskDetails.getStatus());
            task.setDueDate(taskDetails.getDueDate());
            return repository.save(task);
        });
    }

    public boolean delete(Long id) {
        return repository.findById(id).map(task -> {
            repository.delete(task);
            return true;
        }).orElse(false);
    }
}
