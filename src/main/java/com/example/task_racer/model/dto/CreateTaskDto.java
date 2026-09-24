package com.example.task_racer.model.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateTaskDto {

    private String title;

    private String description;

    private Boolean completed;

}
