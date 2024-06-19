--liquibase formatted sql

--changeset rita:2

CREATE TABLE flights
(
    flight_id      INT AUTO_INCREMENT PRIMARY KEY,
    airport_from   VARCHAR(55),
    airport_to     VARCHAR(55),
    carrier        VARCHAR(55),
    departure_time DATETIME,
    arrival_time   DATETIME,
    CONSTRAINT fk_airport_from FOREIGN KEY (airport_from) REFERENCES airports (airport),
    CONSTRAINT fk_airport_to FOREIGN KEY (airport_to) REFERENCES airports (airport)
);
