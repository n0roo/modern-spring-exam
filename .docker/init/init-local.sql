-- local 또는 stage에서는 명시적으로 DB의 물리적 분리 허용이 어려울수 있으므로 참고한다.
create database common_auth;
create database common_sample;

create user rdb_dev with password 'rdb_dev';
grant all privileges on database common_auth to rdb_dev;
alter database common_auth owner to rdb_dev;
grant all privileges on database common_sample to rdb_dev;
alter database common_sample owner to rdb_dev;