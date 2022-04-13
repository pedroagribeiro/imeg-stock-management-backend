CREATE TABLE items (
    id          integer PRIMARY KEY NOT NULL UNIQUE,
    name        varchar(255)        NOT NULL,
    quantity    integer NOT NULL
);

CREATE SEQUENCE item_id_seq START 1;