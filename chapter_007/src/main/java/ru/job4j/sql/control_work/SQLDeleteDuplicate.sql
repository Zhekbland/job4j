/**
 * Create table.
 */
CREATE TABLE cities
(
    id   int,
    name varchar
);

/**
 * Insert duplicates.
 */
INSERT INTO cities
VALUES (1, 'Москва'),
       (2, 'Москва'),
       (3, 'СПб'),
       (4, 'СПб'),
       (5, 'Казань');

/**
 * Delete duplicates.
 */
DELETE
FROM cities
WHERE id > (SELECT MIN(id) FROM cities as X WHERE X.name = cities.name);

/**
 * Set constraint.
 */
ALTER TABLE cities
    ADD CONSTRAINT unique_name UNIQUE (name);