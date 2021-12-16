INSERT INTO user(username, password, created_time)
VALUES ('admin', 'admin', '2021-10-27T10:54:42'),
       ('user', 'user', '2021-10-27T10:54:42');

INSERT INTO university(name, location, creator_id, created_time)
VALUES ('French University In Armenia', '1st Building, 10 Davit Anhaght Street, Yerevan 0037', 1, '2021-10-27T10:54:42');

INSERT INTO student(first_name, last_name, birth_date, faculty, year, degree, creator_id, university_id, created_time)
VALUES ('Hrant', 'Arakelyan', '2001-12-03', 'IMA', 4, 'Bachelor', 1, 1, '2021-10-27T10:54:42'),
       ('Albina', 'Hakobyan', '2000-07-07', 'IMA', 4, 'Bachelor', 1, 1, '2021-10-27T10:54:42'),
       ('Sara', 'Kostandyan', '2001-02-27', 'IMA', 4, 'Bachelor', 1, 1, '2021-10-27T10:54:42');
