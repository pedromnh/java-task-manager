package com.pedromnh.java_task_manager.service;

import com.pedromnh.java_task_manager.model.Status;
import com.pedromnh.java_task_manager.model.Task;
import com.pedromnh.java_task_manager.repository.TaskRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TaskServiceTest {

    private TaskRepository repository;
    private TaskService service;

    @BeforeEach
    void setUp() {
        repository = mock(TaskRepository.class);
        service = new TaskService(repository);
    }

    @Test
    void createTask_ShouldReturnSavedTask() {
        Task task = new Task("Test", "desc", Status.IN_PROGRESS, LocalDate.now().plusDays(1));
        when(repository.save(any(Task.class))).thenReturn(task);

        Task saved = service.create(task);

        assertThat(saved.getTitle()).isEqualTo("Test");
        assertThat(saved.getStatus()).isEqualTo(Status.IN_PROGRESS);
    }

    @Test
    void getById_ShouldReturnTask() {
        Task task = new Task("Test", "desc", Status.IN_PROGRESS, LocalDate.now().plusDays(1));
        when(repository.findById(1L)).thenReturn(Optional.of(task));

        Optional<Task> found = service.getById(1L);

        assertThat(found.get().getTitle()).isEqualTo("Test");
        assertThat(found.get().getStatus()).isEqualTo(Status.IN_PROGRESS);
    }
}
