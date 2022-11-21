create table if not exists users
(
    id       bigint auto_increment
        primary key,
    active   bit          null,
    email    varchar(255) null,
    password varchar(255) null,
    username varchar(255) null
)
    engine = MyISAM;

create table if not exists roles
(
    id   bigint auto_increment
        primary key,
    name varchar(255) null
)
    engine = MyISAM;

create table if not exists user_roles
(
    user_id bigint not null,
    role_id bigint not null,
    primary key (user_id, role_id)
)
    engine = MyISAM;

create index FKh8ciramu9cc9q3qcqiv4ue8a6
    on user_roles (role_id);


# ROLES
insert into roles (name)
select * from (select 'ROLE_ADMIN') as tmp
where not exists (
    select name from roles where name = 'ROLE_ADMIN'
    );

insert into roles (name)
select * from (select 'ROLE_USER') as tmp
where not exists(
    select name from roles where name = 'ROLE_USER'
    );
