CREATE TABLE user_teams (
    user_id integer not null,
    team_id integer not null,
    primary key (user_id, team_id),
    FOREIGN KEY (user_id) REFERENCES users(id),
    FOREIGN KEY (team_id) REFERENCES teams(id)
);