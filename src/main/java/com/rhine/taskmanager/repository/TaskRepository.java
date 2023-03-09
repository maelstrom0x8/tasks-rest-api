package com.rhine.taskmanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rhine.taskmanager.domain.model.Task;

public interface TaskRepository extends JpaRepository<Task, Long> {

}
