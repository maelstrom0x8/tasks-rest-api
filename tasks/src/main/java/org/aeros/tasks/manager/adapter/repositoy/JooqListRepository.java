package org.aeros.tasks.manager.adapter.repositoy;

import static org.aeros.tasks.jooq.tables.Lists.LISTS;
import static org.jooq.Records.mapping;

import org.aeros.tasks.manager.domain.model.TaskList;
import org.aeros.tasks.manager.repository.ListRepository;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class JooqListRepository implements ListRepository {

    private final DSLContext ctx;

    public JooqListRepository(DSLContext ctx) {
        this.ctx = ctx;
    }

    @Override
    public TaskList save(TaskList list) {
        return ctx.insertInto(LISTS)
                .set(LISTS.TITLE, list.title())
                .set(LISTS.OWNER, list.owner())
                .returningResult(LISTS.ID, LISTS.TITLE, LISTS.OWNER)
                .fetchOne(mapping(TaskList::new));
    }

    @Override
    public Optional<TaskList> findById(String user, Long id) {
        return ctx.selectFrom(LISTS)
                .where(LISTS.OWNER.eq(user))
                .and(LISTS.ID.eq(id))
                .fetchOptional(mapping(TaskList::new));
    }

    @Override
    public List<TaskList> findAll(String user) {
        return ctx.selectFrom(LISTS)
                .where(LISTS.OWNER.eq(user))
                .fetch()
                .map(mapping(TaskList::new));
    }

    @Override
    public void deleteAll(String user) {
        ctx.deleteFrom(LISTS).where(LISTS.OWNER.eq(user)).execute();
    }

    @Override
    public void deleteById(String user, Long id) {
        ctx.deleteFrom(LISTS).where(LISTS.OWNER.eq(user)).and(LISTS.ID.eq(id)).execute();
    }

    @Override
    public void update(String user, TaskList list) {
        ctx.update(LISTS)
                .set(LISTS.TITLE, list.title())
                .where(LISTS.OWNER.eq(user).and(LISTS.ID.eq(list.id())))
                .execute();
    }
}
