package com.example.task_racer.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateTaskDto {

    private String title;

    private String description;

    private Boolean completed;

}
