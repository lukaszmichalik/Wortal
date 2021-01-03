CREATE TABLE events(
    id integer PRIMARY KEY,
    date timestamp,
    city text,
    adress text,
	state text,
	type text,
	admin_id integer,
	FOREIGN KEY (admin_id) REFERENCES users(id)
);

CREATE SEQUENCE events_id_seq
AS integer
START WITH 1
INCREMENT BY 1 ;