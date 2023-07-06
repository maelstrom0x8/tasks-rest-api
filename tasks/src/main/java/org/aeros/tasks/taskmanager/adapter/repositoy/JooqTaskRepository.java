package org.aeros.tasks.taskmanager.adapter.repositoy;

import static org.aeros.tasks.jooq.tables.Lists.LISTS;
import static org.jooq.Records.mapping;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import org.aeros.tasks.jooq.tables.Tasks;
import org.aeros.tasks.jooq.tables.records.TasksRecord;
import org.aeros.tasks.taskmanager.domain.model.Task;
import org.aeros.tasks.taskmanager.domain.model.TaskList;
import org.aeros.tasks.taskmanager.repository.TaskRepository;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

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
        create.insertInto(Tasks.TASKS)
                .set(Tasks.TASKS.NAME, task.name())
                .set(Tasks.TASKS.DESCRIPTION, task.description())
                .set(Tasks.TASKS.SCHEDULE, task.schedule().getEpochSecond())
                .set(Tasks.TASKS.COMPLETED, task.completed())
                .set(Tasks.TASKS.LIST_ID, task.listID())
                .returningResult(
                        Tasks.TASKS.ID,
                        Tasks.TASKS.LIST_ID,
                        Tasks.TASKS.NAME,
                        Tasks.TASKS.DESCRIPTION,
                        Tasks.TASKS.SCHEDULE,
                        Tasks.TASKS.COMPLETED)
                .execute();

        return null;
    }

    @Override
    public List<TaskList> findAll(String owner) {
        return create.selectFrom(LISTS).where(LISTS.OWNER.eq(owner)).fetch().map(mapping(TaskList::new));
    }

    @Override
    public List<Task> findAllTasks(String owner, Long listID) {
        return create
                .select(Tasks.TASKS.fields())
                .from(Tasks.TASKS)
                .join(LISTS)
                .on(LISTS.ID.eq(listID))
                .where(LISTS.OWNER.eq(owner))
                .fetchInto(Tasks.TASKS)
                .stream()
                .map(r -> new Task(
                        r.getId(),
                        r.getListId(),
                        r.getName(),
                        r.getDescription(),
                        Instant.ofEpochSecond(r.getSchedule()),
                        r.getCompleted()))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<TaskList> findListById(String owner, Long id) {
        return create.selectFrom(LISTS)
                .where(LISTS.OWNER.eq(owner).and(LISTS.ID.eq(id)))
                .fetchOptional()
                .map(mapping(TaskList::new));
    }

    @Override
    public Task findTaskById(String owner, Long id, Long listID) {
        TasksRecord record = create.select(Tasks.TASKS.fields())
                .from(Tasks.TASKS)
                .join(LISTS)
                .on(LISTS.ID.eq(listID))
                .where(LISTS.OWNER.eq(owner))
                .fetchOptionalInto(Tasks.TASKS)
                .orElseThrow();

        return new Task(
                record.getId(),
                record.getListId(),
                record.getName(),
                record.getDescription(),
                Instant.ofEpochSecond(record.getSchedule()),
                record.getCompleted());
    }

    @Override
    public void deleteAll(String owner) {
        create.deleteFrom(LISTS).where(LISTS.OWNER.eq(owner)).execute();
    }

    @Override
    public void deleteList(String owner, Long listID) {
        create.deleteFrom(Tasks.TASKS).where(Tasks.TASKS.LIST_ID.eq(listID)).execute();
        create.deleteFrom(LISTS).where(LISTS.ID.eq(listID)).execute();
    }

    @Override
    public void deleteTask(String owner, Long taskID) {
        create.deleteFrom(Tasks.TASKS)
                .where(Tasks.TASKS
                        .ID
                        .eq(taskID)
                        .and(Tasks.TASKS.LIST_ID.in(
                                create.select(LISTS.ID).from(LISTS).where(LISTS.OWNER.eq(owner)))))
                .execute();
    }

    @Override
    public void deleteAllTasks(String owner, Long listID) {
        create.deleteFrom(Tasks.TASKS).where(Tasks.TASKS.LIST_ID.eq(listID)).execute();
    }

    @Override
    public void updateTask(Long listID, Task task) {
        create.update(Tasks.TASKS)
                .set(Tasks.TASKS.NAME, task.name())
                .set(Tasks.TASKS.DESCRIPTION, task.description())
                .set(Tasks.TASKS.SCHEDULE, task.schedule().getEpochSecond())
                .set(Tasks.TASKS.COMPLETED, task.completed())
                .where(Tasks.TASKS.LIST_ID.eq(listID))
                .execute();
    }

    @Override
    public void updateList(TaskList list) {
        create.update(LISTS).set(LISTS.TITLE, list.title()).execute();
    }
}
