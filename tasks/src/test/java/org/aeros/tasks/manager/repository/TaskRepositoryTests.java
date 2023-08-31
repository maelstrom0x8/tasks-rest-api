package org.aeros.tasks.manager.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.aeros.tasks.common.AbstractIntegrationTest;
import org.aeros.tasks.jooq.tables.Lists;
import org.jooq.DSLContext;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;

@Sql("/test-data.sql")
public class TaskRepositoryTests extends AbstractIntegrationTest {

    @Autowired private DSLContext ctx;
    @Autowired private TaskRepository taskRepository;

    @Test
    void initialState() {
        long listCount = ctx.selectFrom(Lists.LISTS).stream().count();

        assertEquals(4, listCount);
    }
}
