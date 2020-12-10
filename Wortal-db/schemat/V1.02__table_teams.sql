CREATE TABLE teams (
    id integer PRIMARY KEY,
    name text,
    location text,
	manager_id integer,
    FOREIGN KEY (manager_id) REFERENCES users(id)
);

CREATE SEQUENCE teams_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1;
	
ALTER TABLE users ADD CONSTRAINT fk_team_id FOREIGN KEY (team_id) REFERENCES teams(id);