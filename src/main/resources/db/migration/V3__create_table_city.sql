CREATE TABLE city
(
    id         UUID NOT NULL,
    created_by VARCHAR(30),
    updated_by VARCHAR(30),
    created_at TIMESTAMP,
    updated_at TIMESTAMP,
    name       VARCHAR(30),
    state_id   UUID,
    CONSTRAINT pk_city PRIMARY KEY (id)
);

ALTER TABLE city
    ADD CONSTRAINT FK_CITY_ON_STATE FOREIGN KEY (state_id) REFERENCES state (id);