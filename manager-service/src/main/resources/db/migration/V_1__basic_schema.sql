create schema if not exists user_management;

create table if not exists user_management.t_user
(
    id        serial primary key,
    user_name varchar(50) not null check ( length(trim(user_name)) > 0 ) unique,
    password  varchar(1000)
);

create table if not exists user_management.t_authority
(
    id        serial primary key,
    authority varchar not null check ( length(trim(authority)) > 0 ) unique
);

create table if not exists user_management.t_user_authority
(
    id           serial primary key,
    id_user      int not null references user_management.t_user (id),
    id_authority int not null references user_management.t_authority (id),
    constraint uk_user_authority unique (id_user, id_authority)
);
