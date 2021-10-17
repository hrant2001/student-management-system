INSERT INTO user(username, password)
VALUES ('admin', 'admin'),
       ('user', 'user');

INSERT INTO university(name, location, creator_id)
VALUES ('French University In Armenia', '1st Building, 10 Davit Anhaght Street, Yerevan 0037', 1);

INSERT INTO student(first_name, last_name, birth_date, faculty, year, degree, creator_id, university_id)
VALUES ('Hrant', 'Arakelyan', '2001-12-03', 'IMA', 4, 'Bachelor', 1, 1),
       ('Albina', 'Hakobyan', '2000-07-07', 'IMA', 4, 'Bachelor', 1, 1),
       ('Sara', 'Kostandyan', '2001-02-27', 'IMA', 4, 'Bachelor', 1, 1);
