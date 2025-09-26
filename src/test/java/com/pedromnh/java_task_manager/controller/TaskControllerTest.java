package com.pedromnh.java_task_manager.controller;

import com.pedromnh.java_task_manager.model.Status;
import com.pedromnh.java_task_manager.model.Task;
import com.pedromnh.java_task_manager.service.TaskService;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TaskController.class)
public class TaskControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private TaskService service;

    @Test
    void getAllTasks_ShouldReturnList() throws Exception {
        Task task = new Task("Test", "desc", Status.IN_PROGRESS, LocalDate.now().plusDays(1));
        Mockito.when(service.getAll(null, null)).thenReturn(List.of(task));

        mockMvc.perform(get("/tasks"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].title").value("Test"));
    }

    @Test
    void createTask_ShouldReturnTask() throws Exception {
        Task task = new Task("Test", "desc", Status.IN_PROGRESS, LocalDate.now().plusDays(1));
        Mockito.when(service.create(any(Task.class))).thenReturn(task);

        String json = """
            { "title": "Test", "description": "desc", "status": "IN_PROGRESS", "dueDate": "2025-10-01" }
            """;

        mockMvc.perform(post("/tasks")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Test"));
    }
}
