CREATE TABLE user_roles (
    user_id integer not null,
    role_id integer not null,
    primary key (user_id, role_id),
    FOREIGN KEY (user_id) REFERENCES users(id),
    FOREIGN KEY (role_id) REFERENCES roles(id)
);
