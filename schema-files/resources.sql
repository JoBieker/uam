-- auto-generated definition
create table resources
(
    id           bigint not null
        constraint resources_key
            primary key,
    name         varchar(255),
    description  varchar(255),
    domain       varchar(255),
    path         varchar(255),
    date_created date   not null,
    created_by   varchar(255)
);

alter table resources
    owner to postgres;

grant delete, insert, references, select, trigger, truncate, update on resources to uam_admin with grant option;

