--liquibase formatted sql

--changeset akucher:2
create table bid (
                     id              serial                           not null  primary key ,
                     bidder_name     varchar                          not null,
                     bid_date        timestamp without time zone      not null,
                     lot_id          integer references lot (id)
                    );