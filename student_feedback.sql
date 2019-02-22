drop database studentfeedback;
create database studentfeedback;
use studentfeedback;
create table user(uid int auto_increment,username varchar(100),password varchar(100),constraint primary key(uid));	
create table evaluation_criteria_heading(echid int,text varchar(100),constraint primary key(echid));
create table evaluation_criteria(ecid int auto_increment,echid int,text varchar(100),constraint primary key(ecid),constraint foreign key(echid) references evaluation_criteria_heading(echid));

INSERT INTO `studentfeedback`.`user`
(
`username`,
`password`)
VALUES
('abc123','123');

INSERT INTO `studentfeedback`.`evaluation_criteria_heading`
(`echid`,
`text`)
VALUES
(1,'Enthusiasm'),
(2,'Organization'),
(3,'Lecturer student interaction');


INSERT INTO `studentfeedback`.`evaluation_criteria`
(`echid`,
`text`)
VALUES
(1,'Lecturer was punctual'),
(2,'Lecturer was prepared for lectures'),
(2,'Lectureres were well structured'),
(2,'Details of course content and learning outcomes were provided'),
(3,'Student were encouraged to ask questions'),
(3,'Lecturer appreciated students participation'),
(3,'Student were motivated to learn'),
(3,'Attention was given to the students individually'),
(3,'Feedback provided for the students questions were understood');

SELECT `evaluation_criteria_heading`.`echid`,
    `evaluation_criteria_heading`.`text`
FROM `studentfeedback`.`evaluation_criteria_heading`;

SELECT `evaluation_criteria`.`ecid`,
    `evaluation_criteria`.`echid`,
    `evaluation_criteria`.`text`
FROM `studentfeedback`.`evaluation_criteria`;


SELECT `user`.`uid`,
    `user`.`username`,
    `user`.`password`
FROM `studentfeedback`.`user`;


