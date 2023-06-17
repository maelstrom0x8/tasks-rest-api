/*
 * This file is generated by jOOQ.
 */
package com.redstorm.rhine.jooq;


import com.redstorm.rhine.jooq.tables.FlywaySchemaHistory;
import com.redstorm.rhine.jooq.tables.SchemaVersion;

import org.jooq.Index;
import org.jooq.OrderField;
import org.jooq.impl.DSL;
import org.jooq.impl.Internal;


/**
 * A class modelling indexes of tables in public.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Indexes {

    // -------------------------------------------------------------------------
    // INDEX definitions
    // -------------------------------------------------------------------------

    public static final Index FLYWAY_SCHEMA_HISTORY_S_IDX = Internal.createIndex(DSL.name("flyway_schema_history_s_idx"), FlywaySchemaHistory.FLYWAY_SCHEMA_HISTORY, new OrderField[] { FlywaySchemaHistory.FLYWAY_SCHEMA_HISTORY.SUCCESS }, false);
    public static final Index SCHEMA_VERSION_IR_IDX = Internal.createIndex(DSL.name("schema_version_ir_idx"), SchemaVersion.SCHEMA_VERSION, new OrderField[] { SchemaVersion.SCHEMA_VERSION.INSTALLED_RANK }, false);
    public static final Index SCHEMA_VERSION_S_IDX = Internal.createIndex(DSL.name("schema_version_s_idx"), SchemaVersion.SCHEMA_VERSION, new OrderField[] { SchemaVersion.SCHEMA_VERSION.SUCCESS }, false);
    public static final Index SCHEMA_VERSION_VR_IDX = Internal.createIndex(DSL.name("schema_version_vr_idx"), SchemaVersion.SCHEMA_VERSION, new OrderField[] { SchemaVersion.SCHEMA_VERSION.VERSION_RANK }, false);
}
