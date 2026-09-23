package com.example.task_racer.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import java.util.List;
import com.example.task_racer.model.Task;
import com.example.task_racer.service.TaskService;
import com.example.task_racer.model.dto.CreateTaskDto;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    private TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping
    public List<Task> getAllTasks() {
        return taskService.getAllTasks();
    }

    @PostMapping
    public Task createTask(@RequestBody CreateTaskDto createTaskDto) {
        Task newTask = Task.builder()
                .title(createTaskDto.getTitle())
                .description(createTaskDto.getDescription())
                .completed(createTaskDto.getCompleted())
                .build();

        return taskService.saveTask(newTask);
    }
}
