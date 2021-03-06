
create table type (
	id serial primary key,
	name varchar(2000)
);

create table product (
	id serial primary key,
	name varchar(2000),
	type_id int references type(id),
	expired_date timestamp,
	price money
);

insert into type(name) values ('cheese'), ('milk'), ('icecream');


insert into product(name, type_id, expired_date, price) values ('Mozzarella', 1, '2019-05-08 08:00:00', 400);

insert into product(name, type_id, expired_date, price) values ('Maasdam', 1, '2019-04-08 08:00:00', 600);

insert into product(name, type_id, expired_date, price) values ('Parmezan', 1, '2019-08-08 08:00:00', 480);

insert into product(name, type_id, expired_date, price) values ('Monterey', 1, '2019-06-08 08:00:00', 550);


insert into product(name, type_id, expired_date, price) values ('House in the village', 2, '2019-04-11 08:00:00', 90);

insert into product(name, type_id, expired_date, price) values ('Gorodetskoe', 2, '2019-04-08 08:00:00', 70);

insert into product(name, type_id, expired_date, price) values ('President', 2, '2019-04-08 08:00:00', 120);


insert into product(name, type_id, expired_date, price) values ('Snickers', 3, '2019-09-01 08:00:00', 280);

insert into product(name, type_id, expired_date, price) values ('Icecream Fruit', 3, '2019-08-03 08:00:00', 50);

insert into product(name, type_id, expired_date, price) values ('Gold of Russia', 3, '2019-07-06 08:00:00', 380);

--1. Написать запрос получение всех продуктов с типом "СЫР"
select p.name from product as p join type as t on p.type_id = t.id where t.name = 'cheese';

--2. Написать запрос получения всех продуктов, у кого в имени есть слово "мороженное"
select t.name as product_type, p.name from product as p join type as t on p.type_id = t.id where p.name ilike '%icecream%';

--3. Написать запрос, который выводит все продукты, срок годности которых заканчивается в следующем месяце.
select t.name, p.name, p.expired_date from product as p join type as t on p.type_id = t.id
where expired_date between CURRENT_DATE and CURRENT_DATE + INTERVAL '1 month';

--4. Написать запрос, который выводит самый дорогой продукт.
select t.name as product_name, p.name, p.price from product as p join type as t on p.type_id = t.id
where price = (select max(price) from product);

--5. Написать запрос, который выводит количество всех продуктов определенного типа.
select t.name, count(p.id) as amount from product as p join type as t on p.type_id = t.id where t.name = 'milk'
group by t.name;

--6. Написать запрос получение всех продуктов с типом "СЫР" и "МОЛОКО"
select p.name, t.name from product as p join type as t on p.type_id = t.id where t.name in ('cheese', 'milk');

--7. Написать запрос, который выводит тип продуктов, которых осталось меньше 10 штук.  
select name from type where (select count(p.id) from product as p where p.type_id = type.id) < 4;

--8. Вывести все продукты и их тип.
select p.name, t.name from product as p join type as t on p.type_id = t.id;