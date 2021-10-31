create table users (
    id serial primary key,
    username varchar(100) unique ,
    password varchar(100),
    enabled boolean  default true
);

create table topics(
    id serial primary key,
    name varchar(2000),
    description varchar(2000),
    created timestamp without time zone not null default now(),
    user_id int not null references users(id)
);

create table posts(
    id serial primary key,
    description varchar(2000),
    created timestamp without time zone not null default now(),
    user_id int not null references users(id)
);
