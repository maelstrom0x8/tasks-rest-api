package com.rhine.taskmanager.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.rhine.taskmanager.domain.model.TaskList;

public interface TaskListRepository extends JpaRepository<TaskList, Long> {

    Optional<TaskList> findByTitle(String title);

    @Query("SELECT tl FROM TaskList tl WHERE tl.owner = :user AND tl.id  = :id")
    Optional<TaskList> findByUser(@Param("user") String user, @Param("id") Long id);
    
    @Query("SELECT tl FROM TaskList tl WHERE tl.owner = :user")
    List<TaskList> findAllByUser(@Param("user") String user);
    
    @Modifying
    @Query("DELETE FROM TaskList tl WHERE tl.owner = :user")
    void deleteAllUserList(@Param("user") String user);

    @Query("DELETE FROM TaskList tl WHERE tl.owner = :user AND tl.id = :id")
    void deleteUserListById(@Param("user") String user, @Param("id") Long id);


}
