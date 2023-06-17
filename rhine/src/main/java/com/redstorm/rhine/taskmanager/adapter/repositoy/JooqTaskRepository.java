package com.redstorm.rhine.taskmanager.adapter.repositoy;

import static com.redstorm.rhine.jooq.tables.Lists.LISTS;
import static com.redstorm.rhine.jooq.tables.Tasks.TASKS;

import static org.jooq.Records.mapping;

import com.redstorm.rhine.taskmanager.domain.model.Task;
import com.redstorm.rhine.taskmanager.domain.model.TaskList;
import com.redstorm.rhine.taskmanager.repository.TaskRepository;

import lombok.AllArgsConstructor;

import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
class JooqTaskRepository implements TaskRepository {

    private final DSLContext create;

    @Override
    public TaskList save(TaskList list) {
        return create.insertInto(LISTS)
                .set(LISTS.TITLE, list.title())
                .set(LISTS.OWNER, list.owner())
                .returningResult(LISTS.ID, LISTS.TITLE, LISTS.OWNER)
                .fetchOne(mapping(TaskList::new));
    }

    @Override
    public Task save(Long listID, Task task) {
        create.insertInto(TASKS)
                .set(TASKS.NAME, task.name())
                .set(TASKS.DESCRIPTION, task.description())
                .set(TASKS.SCHEDULE, task.schedule().getEpochSecond())
                .set(TASKS.COMPLETED, task.completed())
                .set(TASKS.LIST_ID, task.listID())
                .returningResult(
                        TASKS.ID,
                        TASKS.LIST_ID,
                        TASKS.NAME,
                        TASKS.DESCRIPTION,
                        TASKS.SCHEDULE,
                        TASKS.COMPLETED)
                .execute();

        return null;
    }

    @Override
    public List<TaskList> findAll(String owner) {
        return create.selectFrom(LISTS)
                .where(LISTS.OWNER.eq(owner))
                .fetch().map(mapping(TaskList::new));
    }

    @Override
    public Optional<TaskList> findByOwner(String owner) {
        return create.selectFrom(LISTS)
                .where(LISTS.OWNER.eq(owner))
                .fetchOptional(mapping(TaskList::new));
    }

    @Override
    public void deleteAll(String owner) {
        create.deleteFrom(LISTS).where(LISTS.OWNER.eq(owner)).execute();
    }

    @Override
    public void deleteTask(String owner, Long listID, Long taskID) {
        create.deleteFrom(TASKS).where(TASKS.ID.eq(taskID)).and(TASKS.LIST_ID.eq(listID)).execute();
    }

    @Override
    public void deleteAllTasks(String owner, Long listID) {
        create.delete(LISTS).where(LISTS.OWNER.eq(owner)).and(LISTS.ID.eq(listID)).execute();
    }
}
