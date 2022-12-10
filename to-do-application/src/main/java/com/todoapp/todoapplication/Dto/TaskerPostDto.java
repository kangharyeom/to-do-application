package com.todoapp.todoapplication.Dto;

import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
public class TaskerPostDto {
    @NotBlank
    private String title;
    private long orderBrother;
    private boolean complete;

}
