package org.aeros.tasks.manager.adapter.repositoy;

import static com.redstorm.rhine.jooq.tables.Lists.LISTS;
import static com.redstorm.rhine.jooq.tables.Tasks.TASKS;

import static org.jooq.Records.mapping;

import org.aeros.tasks.manager.domain.model.Task;
import org.aeros.tasks.manager.domain.model.TaskList;
import org.aeros.tasks.manager.repository.TaskRepository;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
class JooqTaskRepository implements TaskRepository {

    private final DSLContext create;

    JooqTaskRepository(DSLContext create) {
        this.create = create;
    }

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
                .fetch()
                .map(mapping(TaskList::new));
    }

    @Override
    public List<Task> findAll(Long listId) {
        return create.selectFrom(TASKS)
                .where(TASKS.LIST_ID.eq(listId))
                .fetch()
                .map(mapping(Task::new));
    }

    @Override
    public Optional<TaskList> findByOwner(String owner) {
        return create.selectFrom(LISTS)
                .where(LISTS.OWNER.eq(owner))
                .fetchOptional(mapping(TaskList::new));
    }

    @Override
    public TaskList findById(Long id) {
        return null;
    }

    @Override
    public void deleteAll(String owner) {}

    @Override
    public void deleteList(Long listId, String user) {
        create.deleteFrom(LISTS).where(LISTS.ID.eq(listId)).and(LISTS.OWNER.eq(user)).execute();
    }

    @Override
    public void deleteAllList(String owner) {
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
