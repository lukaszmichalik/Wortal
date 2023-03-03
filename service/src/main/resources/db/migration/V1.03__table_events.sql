CREATE TABLE events(
    id serial not null,
    city text,
    address text,
    date DATE,
    time text,
    surface text,
    limitation text,
    description text,
	organizer integer,
	FOREIGN KEY (organizer) REFERENCES users(id),
	primary key (id)
);
