package com.todoapp.todoapplication.Dto;

import lombok.Getter;
import lombok.Setter;


@Getter
public class TaskPatchDto {

    private long taskId;

    private String title;

    private long todo_order;

    private boolean completed;


    public void setTaskId(long taskId) {
        this.taskId = taskId;
    }

}
