-- auto-generated definition
create table accesses
(
    id           bigint not null
        primary key,
    name         varchar(255),
    description  varchar(255),
    role_id      bigint
        constraint fk_accesses_roles_id
            references roles,
    resource_id  bigint
        constraint fk_accesses_resources_id
            references resources,
    read         boolean default true,
    write        boolean default false,
    remove       boolean default false,
    date_created date   not null,
    created_by   varchar(255)
);

alter table accesses
    owner to postgres;

grant delete, insert, references, select, trigger, truncate, update on accesses to uam_admin with grant option;

