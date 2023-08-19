-- liquibase formatted sql

-- changeset kolomnin: 1

create table tasks
(
    id          serial primary key,
    chat_id     bigint,
    date_time   timestamptz,
    description text
)