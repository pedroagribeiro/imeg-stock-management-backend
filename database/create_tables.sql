CREATE TABLE items (
    id          integer PRIMARY KEY NOT NULL UNIQUE,
    name        varchar(255)        NOT NULL UNIQUE,
    quantity    integer             NOT NULL,
    unit        varchar(255)        NOT NULL
);

CREATE TABLE work_sites (
    id          integer PRIMARY KEY NOT NULL UNIQUE,
    code        integer             NOT NULL UNIQUE,
    name        varchar(255)        NOT NULL,
    location    varchar(255)        NOT NULL
);

CREATE TABLE items_by_work_site (
    id              integer PRIMARY KEY NOT NULL UNIQUE,
    item_id         integer             NOT NULL,
    work_site_id    integer             NOT NULL,
    quantity        integer             NOT NULL,
    date            timestamp           NOT NULL,
    CONSTRAINT fk_item FOREIGN KEY(item_id) REFERENCES items(id),
    CONSTRAINT fk_work_site FOREIGN KEY(work_site_id) REFERENCES work_sites(id)
);

CREATE SEQUENCE item_id_seq START 15;
CREATE SEQUENCE work_site_id_seq START 2;
CREATE SEQUENCE items_by_work_site_id_seq START 1;

INSERT INTO items VALUES (1, '1/4 inch', 10, 'metros');
INSERT INTO items VALUES (2, '3/8 inch', 10, 'metros');
INSERT INTO items VALUES (3, '1/2 inch', 10, 'metros');
INSERT INTO items VALUES (4, '5/8 inch', 10, 'metros');
INSERT INTO items VALUES (5, '3/4 inch', 10, 'metros');
INSERT INTO items VALUES (6, '7/8 inch', 10, 'metros');
INSERT INTO items VALUES (7, '1 1/8 inch', 10, 'metros');
INSERT INTO items VALUES (8, '1 3/8 inch', 10, 'metros');
INSERT INTO items VALUES (9, '1 5/8 inch', 10, 'metros');
INSERT INTO items VALUES (10, '2 inch', 10, 'metros');

INSERT INTO items VALUES (11, 'R410A', 10, 'kilogramas');
INSERT INTO items VALUES (12, 'R404', 10, 'kilogramas');
INSERT INTO items VALUES (13, 'R32', 10, 'kilogramas');
INSERT INTO items VALUES (14, 'N2', 10, 'kilogramas');

INSERT INTO work_sites VALUES (1, 1000, 'Armazem', 'Sequeira');
