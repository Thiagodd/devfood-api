CREATE TABLE payment_method
(
    id          UUID NOT NULL,
    created_by  VARCHAR(30),
    updated_by  VARCHAR(30),
    created_at  TIMESTAMP NOT NULL,
    updated_at  TIMESTAMP,
    description VARCHAR(50),

    CONSTRAINT pk_payment_method PRIMARY KEY (id)
);