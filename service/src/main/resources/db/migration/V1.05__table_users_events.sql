CREATE TABLE users_events (
    user_id integer not null,
    event_id integer not null,
    primary key (user_id, event_id),
    FOREIGN KEY (user_id) REFERENCES users(id),
    FOREIGN KEY (event_id) REFERENCES events(id)
);
