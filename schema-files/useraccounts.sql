-- auto-generated definition
create table useraccounts
(
    id           bigint       not null
        constraint useraccounts_key
            primary key,
    employee_id  bigint
        constraint fk_useraccounts_employee_id
            references employees,
    user_name    varchar(255) not null,
    description  varchar(255),
    date_created date         not null,
    created_by   varchar(255) not null,
    date_updated date
);

alter table useraccounts
    owner to postgres;

grant delete, insert, references, select, trigger, truncate, update on useraccounts to uam_admin with grant option;

