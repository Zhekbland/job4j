CREATE TABLE company
(
    id   integer NOT NULL,
    name character varying,
    CONSTRAINT company_pkey PRIMARY KEY (id)
);

CREATE TABLE person
(
    id         integer NOT NULL,
    name       character varying,
    company_id integer,
    CONSTRAINT person_pkey PRIMARY KEY (id)
);

/**
Insert into table company.
 */
INSERT INTO company
VALUES (1, 'SpaceX'),
       (2, 'Tesla'),
       (3, 'Google'),
       (4, 'IBM'),
       (5, 'Oracle');

/**
Insert into table person.
 */
INSERT INTO person
VALUES (1, 'Evgeniy', 1),
       (2, 'John', 1),
       (3, 'Andrew', 1),
       (4, 'Zhenya', 2),
       (5, 'Inna', 2),
       (6, 'Max', 2),
       (7, 'Tom', 1),
       (8, 'Zack', 3),
       (9, 'Petr', 5),
       (10, 'Bob', 4);

/**
Single query:
 - names of all persons that are NOT in the company with id = 5
 - company name for each person
 */
SELECT p.name, c.name AS company
FROM person AS p
         JOIN company AS c ON p.company_id = c.id
WHERE c.id != 5
ORDER BY p.name;

/**
The name of the company with the maximum number of persons + number of persons in this company.
 */
SELECT c.name, COUNT(p.company_id) AS amount_of_members
FROM company AS c
         JOIN person AS p ON c.id = p.company_id
GROUP BY c.name
ORDER BY amount_of_members DESC
LIMIT 1;

