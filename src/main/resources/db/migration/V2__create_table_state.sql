CREATE TABLE state
(
    id         UUID NOT NULL,
    created_by VARCHAR(30),
    updated_by VARCHAR(30),
    created_at TIMESTAMP,
    updated_at TIMESTAMP,
    name       VARCHAR(30),

    CONSTRAINT pk_state PRIMARY KEY (id)
);