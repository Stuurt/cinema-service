--liquibase formatted sql
--changeset renatodam:initial-database-setup logicalFilePath:initial-setup
create table test1 (
                       id int primary key,
                       name varchar(255)
);