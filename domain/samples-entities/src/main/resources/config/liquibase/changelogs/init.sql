-- This script only contains the table creation statements and does not fully represent the table in the database. Do not use it as a backup.

-- Sequence and defined type
create sequence if not exists users_id_seq;

-- Table Definition
create table users (
      "id" int8 not null default nextval('users_id_seq'::regclass),
      "email" varchar not null,
      "name" varchar not null,
      "status" varchar not null,
      "created_at" timestamptz not null default now(),
      "issued_at" timestamptz not null default now(),
      primary key ("id")
);

alter table users owner to rdb_dev;