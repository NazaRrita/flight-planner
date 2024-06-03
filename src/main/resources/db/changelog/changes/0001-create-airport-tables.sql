--liquibase formatted sql

--changeset rita:1

CREATE TABLE airports
(
    country VARCHAR NOT NULL,
    city    VARCHAR NOT NULL,
    airport VARCHAR PRIMARY KEY
);
