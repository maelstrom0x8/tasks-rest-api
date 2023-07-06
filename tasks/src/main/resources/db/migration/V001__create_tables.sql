CREATE SEQUENCE list_id_seq START WITH 1 INCREMENT BY 3;
CREATE SEQUENCE task_id_seq START WITH 1 INCREMENT BY 3;


CREATE TABLE IF NOT EXISTS lists (
    id                  BIGINT              DEFAULT nextval('list_id_seq') NOT NULL,
    title               TEXT             NOT NULL,
    owner               VARCHAR(32)             NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS tasks (
    id                  BIGINT              DEFAULT nextval('task_id_seq') NOT NULL,
    name                TEXT             NOT NULL,
    description         TEXT,
    schedule            BIGINT,
    completed           BOOLEAN             DEFAULT FALSE,
    list_id             BIGINT              NOT NULL REFERENCES lists (id),
    PRIMARY KEY (id)
);
