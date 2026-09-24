package com.example.task_racer.model.dto;

import lombok.Data;

@Data
public class UpdateTaskDto {

    private String title;

    private String description;

    private Boolean completed;

}
