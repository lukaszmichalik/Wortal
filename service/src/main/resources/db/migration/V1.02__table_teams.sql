CREATE TABLE teams (
    id serial not null,
    name text,
    location text,
    creation_date DATE,
    description text,
	manager integer,
    FOREIGN KEY (manager) REFERENCES users(id),
    primary key (id)
);
