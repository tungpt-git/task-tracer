package com.example.task_racer.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import java.util.List;
import com.example.task_racer.model.Task;
import com.example.task_racer.service.TaskService;
import com.example.task_racer.model.dto.CreateTaskDto;
import com.example.task_racer.model.dto.CreateTaskResponseDto;
import com.example.task_racer.model.dto.UpdateTaskDto;
import com.example.task_racer.model.dto.UpdateTaskResponseDto;

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
    public ResponseEntity<CreateTaskResponseDto> createTask(@RequestBody CreateTaskDto createTaskDto) {
        System.out.println("==createTaskDto: " + createTaskDto);
        CreateTaskResponseDto createTaskResponseDto = taskService.createTask(createTaskDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(createTaskResponseDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UpdateTaskResponseDto> updateTask(@PathVariable Long id,
            @RequestBody UpdateTaskDto updateTaskDto) {

        UpdateTaskResponseDto updateTaskResponseDto = taskService.updateTask(id, updateTaskDto);

        return ResponseEntity.status(HttpStatus.OK).body(updateTaskResponseDto);
    }
}
