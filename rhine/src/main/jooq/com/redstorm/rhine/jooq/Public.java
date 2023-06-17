/*
 * This file is generated by jOOQ.
 */
package com.redstorm.rhine.jooq;


import com.redstorm.rhine.jooq.tables.FlywaySchemaHistory;
import com.redstorm.rhine.jooq.tables.Lists;
import com.redstorm.rhine.jooq.tables.SchemaVersion;
import com.redstorm.rhine.jooq.tables.Tasks;
import org.jooq.Catalog;
import org.jooq.Sequence;
import org.jooq.Table;
import org.jooq.impl.SchemaImpl;

import java.util.Arrays;
import java.util.List;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Public extends SchemaImpl {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>public</code>
     */
    public static final Public PUBLIC = new Public();

    /**
     * The table <code>public.flyway_schema_history</code>.
     */
    public final FlywaySchemaHistory FLYWAY_SCHEMA_HISTORY = FlywaySchemaHistory.FLYWAY_SCHEMA_HISTORY;

    /**
     * The table <code>public.lists</code>.
     */
    public final Lists LISTS = Lists.LISTS;

    /**
     * The table <code>public.schema_version</code>.
     */
    public final SchemaVersion SCHEMA_VERSION = SchemaVersion.SCHEMA_VERSION;

    /**
     * The table <code>public.tasks</code>.
     */
    public final Tasks TASKS = Tasks.TASKS;

    /**
     * No further instances allowed
     */
    private Public() {
        super("public", null);
    }


    @Override
    public Catalog getCatalog() {
        return DefaultCatalog.DEFAULT_CATALOG;
    }

    @Override
    public final List<Sequence<?>> getSequences() {
        return Arrays.asList(
            Sequences.LIST_ID_SEQ,
            Sequences.TASK_ID_SEQ
        );
    }

    @Override
    public final List<Table<?>> getTables() {
        return Arrays.asList(
            FlywaySchemaHistory.FLYWAY_SCHEMA_HISTORY,
            Lists.LISTS,
            SchemaVersion.SCHEMA_VERSION,
            Tasks.TASKS
        );
    }
}
