package com.todoapp.todoapplication.Dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class TaskResponseDto {
    private long taskId;
    private String title;
    private long todo_order;
    private boolean completed;

}
