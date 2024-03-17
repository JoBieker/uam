create sequence uam.roles_seq;

alter sequence uam.roles_seq owner to postgres;

grant usage on sequence uam.roles_seq to uam_admin;

