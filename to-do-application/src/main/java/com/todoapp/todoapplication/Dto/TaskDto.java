package com.todoapp.todoapplication.Dto;

import lombok.Getter;

import javax.validation.constraints.NotBlank;


@Getter
public class TaskDto {
    @NotBlank
    private long taskId;

    @NotBlank
    private String title;

    @NotBlank
    private long todo_order;


}
