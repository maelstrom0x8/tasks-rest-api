package com.rhine.redstorm.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rhine.redstorm.model.TaskList;

public interface TaskListRepository extends JpaRepository<TaskList, Long> {

    Optional<TaskList> findByName(String name);
}
