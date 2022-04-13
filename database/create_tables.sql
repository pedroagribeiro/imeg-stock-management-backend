CREATE TABLE items (
    id          integer PRIMARY KEY NOT NULL UNIQUE,
    name        varchar(255)        NOT NULL,
    quantity    integer NOT NULL
);

CREATE TABLE work_sites (
    id          integer PRIMARY KEY NOT NULL UNIQUE,
    name        varchar(255)        NOT NULL,
    location    varchar(255)        NOT NULL
);

CREATE TABLE items_by_work_site (
    id              integer PRIMARY KEY NOT NULL UNIQUE,
    item_id         integer             NOT NULL,
    work_site_id    integer             NOT NULL,
    quantity        integer             NOT NULL,
    CONSTRAINT fk_item FOREIGN KEY(item_id) REFERENCES items(id),
    CONSTRAINT fk_work_site FOREIGN KEY(work_site_id) REFERENCES work_sites(id)
);

CREATE SEQUENCE item_id_seq START 1;
CREATE SEQUENCE work_site_id_seq START 1;
CREATE SEQUENCE items_by_work_site_id_seq START 1;
