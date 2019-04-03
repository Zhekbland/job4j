create database job4j;

create table roles (
	id serial primary key,
	role varchar(2000)
);

create table users (
	id serial primary key,
	name varchar(2000),
	role_id int references roles(id)
);

create table rules (
	id serial primary key,
	rule varchar(2000)
);

create table roles_and_rules (
	id serial primary key,
	role_id int references roles(id),
	rule_id int references rules(id)
);

create table categories (
	id serial primary key,
	category varchar(2000)
);

create table states (
	id serial primary key,
	state varchar(2000)
);

create table items (
	id serial primary key,
	item varchar(2000),
	user_id int references users(id),
	category_id int references categories(id),
	state_id int references states(id)
);

create table commentes (
	id serial primary key,
	comment varchar(2000),
	item_id int references items(id)
);


create table attaches (
	id serial primary key,
	attache varchar(2000),
	item_id int references items(id)
);




insert into roles(role) values('President');
insert into roles(role) values('Director');
insert into roles(role) values('Manager');
insert into roles(role) values('Worker');

insert into users(name, role_id) values('Zhenya', 13);
insert into users(name, role_id) values('Inna', 15);
insert into users(name, role_id) values('Max', 16);
insert into users(name, role_id) values('Petr', 14);

insert into rules(rule) values('with no restrictions');
insert into rules(rule) values('with restrictions');
insert into rules(rule) values('limited');

insert into roles_and_rules(role_id, rule_id) values(13, 4);
insert into roles_and_rules(role_id, rule_id) values(14, 4);
insert into roles_and_rules(role_id, rule_id) values(15, 5);
insert into roles_and_rules(role_id, rule_id) values(16, 6);

insert into categories(category) values('extra');
insert into categories(category) values('important');
insert into categories(category) values('normal');

insert into states(state) values('done');
insert into states(state) values('performed');
insert into states(state) values('pending');

insert into items(item, user_id, category_id, state_id) values('meeting', 7, 4, 6);
insert into items(item, user_id, category_id, state_id) values('delivery', 9, 6, 5);

insert into commentes(comment, item_id) values('everyone should be', 1);
insert into commentes(comment, item_id) values('do it every day', 2);

insert into attaches(attache, item_id) values('a lot of files', 4);
insert into attaches(attache, item_id) values('nothing', 5);

