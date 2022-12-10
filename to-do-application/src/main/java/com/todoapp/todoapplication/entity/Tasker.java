package com.todoapp.todoapplication.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class Tasker {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int taskerId;

    @Column(length = 20, nullable = false)
    private String title;

    @Column(length = 20)
    private int orderBrother;

    private boolean complete;

}
