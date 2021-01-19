INSERT INTO events VALUES(101, 'ul. Kwiatowa', 'Wrocław','1970-10-12','Cześć, tu Michał organizator. Sptykamy się 15 min przed czasem aby ustalić składy i się rozgrzać. Do zobaczenia!',  '12', 'hala', '21:30',  3);
INSERT INTO events VALUES(102, 'ul. Grzybowa', 'Kraków', '1970-10-12','Cześć, tu Michał organizator. Sptykamy się 15 min przed czasem aby ustalić składy i się rozgrzać. Do zobaczenia!', '10', 'tartan', '11:30', 2);
INSERT INTO events VALUES(103, 'ul. Fiołkowa', 'Bytom', '1970-10-12','Cześć, tu Michał organizator. Sptykamy się 15 min przed czasem aby ustalić składy i się rozgrzać. Do zobaczenia!', '14', 'hala', '21:00', 2);
INSERT INTO events VALUES(104, 'ul. Makowa', 'Zabrze', '1970-10-12','Cześć, tu Michał organizator. Sptykamy się 15 min przed czasem aby ustalić składy i się rozgrzać. Do zobaczenia!','16','naturalna', '05:30', 1);
INSERT INTO events VALUES(105, 'ul. Masłowa', 'Gliwice', '1970-10-12','Cześć, tu Michał organizator. Sptykamy się 15 min przed czasem aby ustalić składy i się rozgrzać. Do zobaczenia!','24', 'naturalna','07:30', 1);
INSERT INTO events VALUES(106, 'ul. Sienkiewicza', 'Katowice', '1970-10-12','Cześć, tu Michał organizator. Sptykamy się 15 min przed czasem aby ustalić składy i się rozgrzać. Do zobaczenia!','12','tartan','21:30', 1);
INSERT INTO events VALUES(107, 'ul. Krakowska', 'Poznań', '1970-10-12','Cześć, tu Michał organizator. Sptykamy się 15 min przed czasem aby ustalić składy i się rozgrzać. Do zobaczenia!', '12', 'tartan','08:30', 1 );
INSERT INTO events VALUES(108, 'ul. Piłsudzkiego', 'Lublin', '1970-10-12','Cześć, tu Michał organizator. Sptykamy się 15 min przed czasem aby ustalić składy i się rozgrzać. Do zobaczenia!','10', 'sztuczna', '15:30', 2 );
INSERT INTO events VALUES(109, 'ul. Krzakowska', 'Toruń', '1970-10-12','Cześć, tu Michał organizator. Sptykamy się 15 min przed czasem aby ustalić składy i się rozgrzać. Do zobaczenia!','8', 'sztuczna', '16:30',3 );

INSERT INTO user_events VALUES(1, 101);
INSERT INTO user_events VALUES(1, 102);
INSERT INTO user_events VALUES(1, 103);
INSERT INTO user_events VALUES(2, 101);
INSERT INTO user_events VALUES(2, 102);
INSERT INTO user_events VALUES(3, 101);
INSERT INTO user_events VALUES(1, 104);
INSERT INTO user_events VALUES(1, 105);
INSERT INTO user_events VALUES(3, 104);
INSERT INTO user_events VALUES(3, 105);

INSERT INTO teams VALUES(101,'1976-10-12','Ten klub zrzesza najlepszych piłkarzy z Wrocka', 'Wrocław', 'FC Tigers',  1);
INSERT INTO teams VALUES(102,'2000-10-12','Byki to klub z tradycjami.','Kraków', 'TG Bulls', 2);
INSERT INTO teams VALUES(103,'2010-10-12','Obchodzimy dziesięciolecie, zapraszamy wszystkich członków na uczte piłkarską','Bytom', 'Piłkarskie Aligatory', 3);

UPDATE users SET team=101 WHERE id = 1;
UPDATE users SET team=102 WHERE id = 2;
UPDATE users SET team=103 WHERE id = 3;