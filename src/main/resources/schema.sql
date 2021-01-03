create table users (
    id                      varchar not null primary key,
    email                   varchar not null unique,
    password                varchar not null,
    isEnabled               boolean,
    isAccountNonExpired     boolean,
    isAccountNonLocked      boolean,
    isCredentialsNonExpired boolean,
    authorities             varchar
);

create table authorities (
    id          varchar not null,
    authority   varchar not null,
    constraint fk_authorities_users foreign key(id) references users(id)
);

-- create unique index idx_auth_user on authorities(id, authority);
