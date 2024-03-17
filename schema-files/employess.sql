-- auto-generated definition
create table employees
(
    id            bigint       not null
        primary key,
    unit_id       bigint
        constraint fk_employees_unit_id
            references units,
    last_name     varchar(255) not null,
    first_name    varchar(255),
    person_number integer      not null,
    created_by    varchar(255) not null,
    date_created  date         not null,
    updated_by    varchar(255),
    date_updated  date
);

alter table employees
    owner to postgres;

grant delete, insert, references, select, trigger, truncate, update on employees to uam_admin with grant option;

