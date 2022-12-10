package com.todoapp.todoapplication.repository;

import com.todoapp.todoapplication.entity.Tasker;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TaskerRepository extends JpaRepository<Tasker, Long> {

    Optional<Tasker> findByTitle(String title);
}
