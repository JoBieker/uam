-- auto-generated definition
create table bearerships
(
    id             bigint not null
        primary key,
    useraccount_id bigint
        constraint fk_bearerships_useraccount_id
            references useraccounts,
    role_id        bigint
        constraint fk_bearerships_role_id
            references roles,
    date_issued    date   not null,
    date_revoked   date,
    date_created   date,
    created_by     varchar(255)
);

alter table bearerships
    owner to postgres;

grant delete, insert, references, select, trigger, truncate, update on bearerships to uam_admin with grant option;

