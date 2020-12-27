CREATE TABLE users(
    id integer PRIMARY KEY,
    fullname text,
    position text,
    age text,
	role text,
	email text  NOT NULL unique,
    password text NOT NULL,
	team_id integer
);

CREATE SEQUENCE users_id_seq
AS integer
START WITH 1
INCREMENT BY 1 ;
