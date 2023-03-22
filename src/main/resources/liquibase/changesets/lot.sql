--liquibase formatted sql

--changeset akucher:1
create table lot (
     id          SERIAL        not null  primary key ,
     status      varchar       not null,
     title       varchar(64)   not null,
     description varchar(4096) not null,
     start_price integer       not null,
     bid_price   integer       not null
);