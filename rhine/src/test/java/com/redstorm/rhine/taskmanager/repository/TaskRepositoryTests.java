package com.redstorm.rhine.taskmanager.repository;

import com.redstorm.rhine.common.AbstractIntegrationTest;
import com.redstorm.rhine.jooq.tables.Lists;
import org.jooq.DSLContext;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jooq.JooqTest;
import org.springframework.test.context.jdbc.Sql;

import static org.junit.jupiter.api.Assertions.assertEquals;

@JooqTest(
        properties = {
            "spring.test.database.replace=none",
            "spring.datasource.url=jdbc:tc:postgresql:15.2-alpine:///db"
        })
@Sql("classpath:test-data.sql")
@Disabled("Will enable after bug-fixes")
public class TaskRepositoryTests extends AbstractIntegrationTest {

    @Autowired private DSLContext ctx;
    @Autowired private TaskRepository taskRepository;

    @Test
    void initialState() {
        long listCount = ctx.selectFrom(Lists.LISTS).stream().count();

        assertEquals(9, listCount);
    }
}
