CREATE DATABASE items_db;

create table rules (
	id serial primary key,
	description text
);

create table role (
	id serial primary key,
	name character varying (2000)
);

create table role_rules (
	id serial primary key,
	role_id integer references role(id),
	rules_id integer references rules(id)
);

create table users (
	id serial primary key,
	name character varying (2000),
	role_id integer references role(id)
);

create table category (
	id serial primary key,
	name character varying (2000)
);

create table state (
	id serial primary key,
	description character varying (2000)
);

create table items (
	id serial primary key,
	description text,
	category_id integer references category(id),
	state_id integer references state(id)
);

create table comments (
	id serial primary key,
	description text,
	item_id integer references items(id)
);

create table attachs (
	id serial primary key,
	source text,
	item_id integer references items(id)
);

insert into role(name) values ('Item creator');
insert into rules(description) values ('Create items');
insert into category(name) values ('Bugs');
insert into state(description) values ('in progress');
insert into state(description) values ('complete');
