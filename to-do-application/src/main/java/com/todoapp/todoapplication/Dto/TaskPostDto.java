package com.todoapp.todoapplication.Dto;

import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
public class TaskPostDto {
    @NotBlank
    private String title;
    private long todo_order;
    private boolean completed;

}
