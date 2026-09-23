package com.example.task_racer.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.task_racer.model.Task;

public interface TaskRepository extends JpaRepository<Task, String> {

}
