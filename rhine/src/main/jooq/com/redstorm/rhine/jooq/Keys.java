/*
 * This file is generated by jOOQ.
 */
package com.redstorm.rhine.jooq;


import com.redstorm.rhine.jooq.tables.Lists;
import com.redstorm.rhine.jooq.tables.SchemaVersion;
import com.redstorm.rhine.jooq.tables.Tasks;
import com.redstorm.rhine.jooq.tables.records.ListsRecord;
import com.redstorm.rhine.jooq.tables.records.SchemaVersionRecord;
import com.redstorm.rhine.jooq.tables.records.TasksRecord;
import org.jooq.ForeignKey;
import org.jooq.TableField;
import org.jooq.UniqueKey;
import org.jooq.impl.DSL;
import org.jooq.impl.Internal;


/**
 * A class modelling foreign key relationships and constraints of tables in
 * public.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Keys {

    // -------------------------------------------------------------------------
    // UNIQUE and PRIMARY KEY definitions
    // -------------------------------------------------------------------------

    public static final UniqueKey<ListsRecord> LISTS_PKEY = Internal.createUniqueKey(Lists.LISTS, DSL.name("lists_pkey"), new TableField[] { Lists.LISTS.ID }, true);
    public static final UniqueKey<SchemaVersionRecord> SCHEMA_VERSION_PK = Internal.createUniqueKey(SchemaVersion.SCHEMA_VERSION, DSL.name("schema_version_pk"), new TableField[] { SchemaVersion.SCHEMA_VERSION.VERSION }, true);
    public static final UniqueKey<TasksRecord> TASKS_PKEY = Internal.createUniqueKey(Tasks.TASKS, DSL.name("tasks_pkey"), new TableField[] { Tasks.TASKS.ID }, true);

    // -------------------------------------------------------------------------
    // FOREIGN KEY definitions
    // -------------------------------------------------------------------------

    public static final ForeignKey<TasksRecord, ListsRecord> TASKS__TASKS_LIST_ID_FKEY = Internal.createForeignKey(Tasks.TASKS, DSL.name("tasks_list_id_fkey"), new TableField[] { Tasks.TASKS.LIST_ID }, Keys.LISTS_PKEY, new TableField[] { Lists.LISTS.ID }, true);
}
