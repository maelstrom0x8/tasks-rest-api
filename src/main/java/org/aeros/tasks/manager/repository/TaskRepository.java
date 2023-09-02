package org.aeros.tasks.manager.repository;

import org.aeros.tasks.manager.domain.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {

    @Query("SELECT t FROM Task t WHERE t.list.id = :listId")
    List<Task> findAllByListId(@Param("listId") Long listId);
}
