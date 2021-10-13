create database if not exists stud_man_sys;

--use stud_man_sys;

create table if not exists user(id int primary key auto_increment,
username varchar(50) not null unique,password varchar(50) not null);

create table if not exists university(id int primary key auto_increment,
university_name varchar(60) not null unique, location varchar(60),
creator_id int not null,FOREIGN KEY (creator_id) REFERENCES user(user_id));

create table if not exists student(id int primary key auto_increment, first_name varchar(30) not null,
last_name varchar(40) not null, birth_date date not null, faculty varchar(30) not null, year tinyint, degree varchar(30),
creator_id int not null, university_id int not null,
FOREIGN KEY (creator_id) REFERENCES user(user_id), FOREIGN KEY (university_id) REFERENCES university(university_id));