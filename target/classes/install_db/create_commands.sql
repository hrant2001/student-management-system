CREATE DATABASE IF NOT EXISTS stud_man_sys;

# use stud_man_sys;

CREATE TABLE IF NOT EXISTS user
(
    id       INT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(50) NOT NULL
);

CREATE TABLE IF NOT EXISTS university
(
    id         INT PRIMARY KEY AUTO_INCREMENT,
    name       VARCHAR(60) NOT NULL UNIQUE,
    location   VARCHAR(60),
    creator_id INT         NOT NULL,
    FOREIGN KEY (creator_id) REFERENCES user (id)
);

CREATE TABLE IF NOT EXISTS student
(
    id            INT PRIMARY KEY AUTO_INCREMENT,
    first_name    VARCHAR(30) NOT NULL,
    last_name     VARCHAR(40) NOT NULL,
    birth_date    DATE        NOT NULL,
    faculty       VARCHAR(30) NOT NULL,
    year          TINYINT,
    degree        VARCHAR(30),
    creator_id    INT         NOT NULL,
    university_id INT         NOT NULL,
    FOREIGN KEY (creator_id) REFERENCES user (id),
    FOREIGN KEY (university_id) REFERENCES university (id)
);