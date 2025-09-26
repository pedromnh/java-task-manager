package com.pedromnh.java_task_manager.repository;

import com.pedromnh.java_task_manager.model.Status;
import com.pedromnh.java_task_manager.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface TaskRepository extends JpaRepository <Task, Long> {
    List<Task> findByStatus(Status status);
    List<Task> findByDueDateBefore(LocalDate dueDate);
    List<Task> findByStatusAndDueDateBefore(Status status, LocalDate dueDate);
}
