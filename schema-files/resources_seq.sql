create sequence uam.resources_seq;

alter sequence uam.resources_seq owner to postgres;

grant usage on sequence uam.resources_seq to uam_admin;

