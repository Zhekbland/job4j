create table body (
	id serial primary key,
	name varchar(200)
);

create table engine (
	id serial primary key,
	name varchar(200)
);

create table transmition (
	id serial primary key,
	name varchar(200)
);

create table car (
	id serial primary key,
	name varchar(200),
	body_id int references body(id),
	engine_id int references engine(id),
	transmition_id int references transmition(id)
);

insert into body(name) values ('sedan'), ('hatchback'), ('cabriolet'), ('limousine');
insert into engine(name) values ('petrol'), ('disel'), ('electric'), ('gas');
insert into transmition(name) values ('manual'), ('automatic'), ('robotics'), ('variator');

insert into car(name, body_id, engine_id, transmition_id) values ('opel_astra_h', 1, 1, 3),
('toyota_corolla', 2, 2, 1), ('tesla', 3, 3, 2);	

--1. Вывести список всех машин и все привязанные к ним детали.
select * from car;

--2. Вывести отдельно детали, которые не используются в машине, кузова, двигатели, коробки передач.
select b.name from body as b left join car as c on b.id = c.body_id where c.body_id is null;
select e.name from engine as e left join car as c on e.id = c.engine_id where c.engine_id is null;
select t.name from transmition as t left join car as c on t.id = c.transmition_id where c.transmition_id is null;