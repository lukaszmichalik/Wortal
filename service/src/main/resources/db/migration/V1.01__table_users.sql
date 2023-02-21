CREATE TABLE users(
    id serial not null ,
    name text,
    position text,
    dob DATE,
    email text  NOT NULL unique,
    username text,
    password text NOT NULL,
    primary key (id)
);
