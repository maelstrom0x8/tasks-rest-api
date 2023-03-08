package com.rhine.taskmanager.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.rhine.taskmanager.domain.model.TaskList;

public interface TaskListRepository extends JpaRepository<TaskList, Long> {

    Optional<TaskList> findByTitle(String title);

    @Query(value = "SELECT * FROM list WHERE owner = :user AND id  = :id", nativeQuery = true)
    Optional<TaskList> findByUser(@Param("user") String user, @Param("id") Long id);

    @Query(value = "SELECT * FROM list WHERE owner = :user", nativeQuery = true)
    List<TaskList> findAllByUser(@Param("user") String user);
}
