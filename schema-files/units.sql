-- auto-generated definition
create table units
(
    id             bigint       not null
        constraint units_key
            primary key,
    name           varchar(255),
    head_id        bigint
        constraint fk_units_head_id
            references employees,
    parent_unit_id bigint
        constraint fk_units_parent_id
            references units,
    description    varchar(255),
    date_created   date         not null,
    created_by     varchar(255) not null,
    date_updated   date,
    user_name      varchar
);

alter table units
    owner to postgres;

grant delete, insert, references, select, trigger, truncate, update on units to uam_admin with grant option;

