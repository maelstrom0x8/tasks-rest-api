package org.aeros.tasks.manager.adapter.repositoy;

import static org.aeros.tasks.jooq.tables.Lists.LISTS;
import static org.aeros.tasks.jooq.tables.Tasks.TASKS;

import org.aeros.tasks.jooq.tables.records.TasksRecord;
import org.aeros.tasks.manager.domain.model.Task;
import org.aeros.tasks.manager.repository.TaskRepository;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;

@Repository
class JooqTaskRepository implements TaskRepository {

    private final DSLContext create;

    JooqTaskRepository(DSLContext create) {
        this.create = create;
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
    public List<Task> findAll(Long listId) {
        return create.selectFrom(TASKS).where(TASKS.LIST_ID.eq(listId)).fetch().map(this::from);
    }

    @Override
    public void deleteSingle(String owner, Long listId, Long taskId) {
        create.deleteFrom(TASKS).where(TASKS.ID.eq(taskId)).and(TASKS.LIST_ID.eq(listId)).execute();
    }

    @Override
    public void update(Long listId, Task task) {
        create.update(TASKS)
                .set(TASKS.NAME, task.name())
                .set(TASKS.DESCRIPTION, task.description())
                .set(TASKS.SCHEDULE, task.schedule().getEpochSecond())
                .set(TASKS.COMPLETED, task.completed())
                .where(TASKS.ID.eq(task.id()).and(TASKS.LIST_ID.eq(listId)))
                .execute();
    }

    @Override
    public void deleteAll(String owner, Long listId) {
        create.delete(LISTS).where(LISTS.OWNER.eq(owner)).and(LISTS.ID.eq(listId)).execute();
    }

    private Task from(TasksRecord record) {
        return new Task(
                record.getId(),
                record.getName(),
                record.getDescription(),
                Instant.ofEpochSecond(record.getSchedule()),
                record.getCompleted(),
                record.getListId());
    }
}
