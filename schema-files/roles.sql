-- auto-generated definition
create table roles
(
    id               bigint not null
        primary key,
    title            varchar(255),
    description      varchar(255),
    unit_id          bigint
        constraint fk_roles_unit_id
            references units,
    targets          text,
    tasks            text,
    responsibilities text,
    notes            text,
    date_created     date,
    date_approved    date,
    created_by       varchar(255),
    approved_by      varchar(255)
);

alter table roles
    owner to postgres;

grant delete, insert, references, select, trigger, truncate, update on roles to uam_admin with grant option;

