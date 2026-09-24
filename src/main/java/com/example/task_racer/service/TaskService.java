package com.example.task_racer.service;

import com.example.task_racer.repository.TaskRepository;
import com.example.task_racer.model.Task;
import com.example.task_racer.model.dto.CreateTaskDto;
import com.example.task_racer.model.dto.UpdateTaskDto;
import com.example.task_racer.model.dto.UpdateTaskResponseDto;
import com.example.task_racer.model.dto.CreateTaskResponseDto;
import com.example.task_racer.exception.ResourceNotFoundException;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    public Task saveTask(Task task) {
        return taskRepository.save(task);
    }

    public void deleteTask(String id) {
        taskRepository.deleteById(id);
    }

    public CreateTaskResponseDto createTask(CreateTaskDto taskDto) {
        Task task = Task.builder()
                .title(taskDto.getTitle())
                .description(taskDto.getDescription())
                .completed(taskDto.getCompleted())
                .build();

        taskRepository.save(task);

        return CreateTaskResponseDto.builder()
                .id(task.getId())
                .title(task.getTitle())
                .description(task.getDescription())
                .completed(task.getCompleted())
                .build();
    }

    public UpdateTaskResponseDto updateTask(Long id, UpdateTaskDto updateTaskDto) {
        Task existingTask = taskRepository.findById(id.toString())
                .orElseThrow(() -> new ResourceNotFoundException("Task not found with id: " + id));

        existingTask.setTitle(updateTaskDto.getTitle());
        existingTask.setDescription(updateTaskDto.getDescription());
        existingTask.setCompleted(updateTaskDto.getCompleted());

        taskRepository.save(existingTask);

        return UpdateTaskResponseDto.builder()
                .id(existingTask.getId())
                .title(existingTask.getTitle())
                .description(existingTask.getDescription())
                .completed(existingTask.getCompleted())
                .build();
    }
}
