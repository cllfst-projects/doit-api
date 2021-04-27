create table users (
    id                      varchar(255) not null primary key,
    email                   varchar(255) not null unique,
    password                varchar(255) not null,
    isEnabled               boolean,
    isAccountNonExpired     boolean,
    isAccountNonLocked      boolean,
    isCredentialsNonExpired boolean,
    authorities             varchar(255)
);

create table authorities (
    id          varchar(255) not null,
    authority   varchar(255) not null,
    constraint fk_authorities_users foreign key(id) references users(id)
);

-- create unique index idx_auth_user on authorities(id, authority);
