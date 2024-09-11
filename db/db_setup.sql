DROP DATABASE IF EXISTS records;
CREATE DATABASE records;

\c records;

CREATE TABLE albums
(
    id SERIAL PRIMARY KEY,
    recordName VARCHAR,
    artist VARCHAR,
    yearOfRelease INT,
    genre VARCHAR,
    quantityInStock INT,
    isAvailable VARCHAR
);
