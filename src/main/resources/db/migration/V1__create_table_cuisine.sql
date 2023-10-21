CREATE TABLE cuisine
(
    id         UUID PRIMARY KEY,
    created_by VARCHAR(30) NULL,
    updated_by VARCHAR(30) NULL ,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NULL ,
    name       VARCHAR(30) NOT NULL
);

