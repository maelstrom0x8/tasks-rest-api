package org.aeros.tasks.manager.repository;

import org.aeros.tasks.manager.domain.model.TaskList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ListRepository extends JpaRepository<TaskList, Long> {

    @Query("SELECT t FROM TaskList t WHERE t.owner = :owner AND t.id = :id")
    Optional<TaskList> findById(@Param("owner") String user, @Param("id") Long id);

    @Query("SELECT t FROM TaskList t WHERE t.owner = :owner")
    List<TaskList> findAllByUser(@Param("owner") String user);

    @Query("DELETE FROM TaskList t WHERE t.owner = :owner")
    void deleteAll(@Param("owner") String user);

    @Query("DELETE FROM TaskList t WHERE t.owner = :owner AND t.id = :id")
    void deleteById(@Param("owner") String user, @Param("id") Long id);
}
