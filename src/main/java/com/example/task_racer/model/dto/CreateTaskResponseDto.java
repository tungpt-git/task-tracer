package com.example.task_racer.model.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateTaskResponseDto {

    private String id;

    private String title;

    private String description;

    private Boolean completed;

}
