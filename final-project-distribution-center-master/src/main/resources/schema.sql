drop table if exists Distribution_Center;
create table Distribution_Center (
    id SERIAL primary key,
    name varchar(255) not null,
    latitude float not null,
    longitude float not null
);

drop table if exists Item;
create table Item(
id SERIAL primary key,
name varchar(255) not null,
create_Year int not null,
created_At timestamp not null,
quantity int not null,
price float not null,
brand_from smallint not null,
distribution_Center_id SERIAL not null
);

drop table if exists users;