INSERT INTO user(username, password, created_time)
VALUES ('test_user1', 'test_pass1', '2021-10-27T10:54:42'),
       ('test_user2', 'test_pass2', '2021-10-27T10:54:42');

INSERT INTO university(name, location, creator_id, created_time)
VALUES ('test_uni', 'test', 1, '2021-10-27T10:54:42');

INSERT INTO student(first_name, last_name, birth_date, faculty, year, degree, image_name, creator_id, university_id, created_time)
VALUES ('Hrant', 'Arakelyan', '2001-12-03', 'IMA', 4, 'Bachelor', 'student1.jpg', 1, 1, '2021-10-27T10:54:42'),
       ('Albina', 'Hakobyan', '2000-07-07', 'IMA', 4, 'Bachelor', 'student2.jpg', 1, 1, '2021-10-27T10:54:42'),
       ('Sara', 'Kostandyan', '2001-02-27', 'IMA', 4, 'Bachelor', 'student3.jpg', 1, 1, '2021-10-27T10:54:42');
