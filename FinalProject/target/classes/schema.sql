create table if not exists Item (
  id identity,
  name varchar(50) not null,
  create_year int not null,
  price double not null,
  brand_from varchar(50) not null,
  created_at timestamp not null
);