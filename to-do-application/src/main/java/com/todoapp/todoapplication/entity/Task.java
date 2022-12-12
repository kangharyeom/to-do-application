package com.todoapp.todoapplication.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long taskId;

    @Column(length = 20, nullable = false)
    private String title;

    @Column(length = 20)
    private long todo_order;

    @Column(nullable = false)
    private boolean completed;

}