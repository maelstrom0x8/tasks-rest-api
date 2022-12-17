package com.rhine.redstorm.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rhine.redstorm.model.Task;

public interface TaskRepository extends JpaRepository<Task, Long> {
    
    Optional<Task> findByName(String name);
}
