CREATE TABLE restaurant
(
    id           UUID NOT NULL,
    created_by   VARCHAR(30),
    updated_by   VARCHAR(30),
    created_at   TIMESTAMP NOT NULL ,
    updated_at   TIMESTAMP,
    name         VARCHAR(30),
    delivery_fee DECIMAL,
    active       BOOLEAN NOT NULL,
    open         BOOLEAN NOT NULL,
    cuisine_id   UUID NOT NULL,
    CONSTRAINT pk_restaurant PRIMARY KEY (id)
);

ALTER TABLE restaurant
    ADD CONSTRAINT FK_RESTAURANT_ON_CUISINE FOREIGN KEY (cuisine_id) REFERENCES cuisine (id);