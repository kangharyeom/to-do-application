package com.todoapp.todoapplication.Dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class TaskerResponseDto {
    private long taskerId;
    private String title;
    private String orderBrother;
    private boolean complete;

}
