@org.hibernate.annotations.GenericGenerator(name = "ID_GENERATOR", strategy = "enhanced-sequence", parameters = {
        @org.hibernate.annotations.Parameter(name = "sequence_name", value = "RHINE_SEQUENCE"),
        @org.hibernate.annotations.Parameter(name = "initial_value", value = "1")
})
package com.rhine.taskmanager.domain.model;
