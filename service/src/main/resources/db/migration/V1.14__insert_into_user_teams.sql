-- assigning managers to their teams
INSERT INTO user_teams VALUES(101, 101);
INSERT INTO user_teams VALUES(102, 102);
INSERT INTO user_teams VALUES(103, 103);

-- inserting team members
INSERT INTO user_teams VALUES(101, 102);
INSERT INTO user_teams VALUES(101, 103);
INSERT INTO user_teams VALUES(102, 101);
INSERT INTO user_teams VALUES(102, 103);
INSERT INTO user_teams VALUES(103, 101);
INSERT INTO user_teams VALUES(103, 102);
