drop database studentfeedback;
create database studentfeedback;
use studentfeedback;
create table faculty(facid int auto_increment,name varchar(100),constraint primary key(facid));
create table degree(degid int auto_increment,facid int,name varchar(100),constraint primary key(degid),constraint foreign key(facid) references faculty(facid));
create table batch(batchid int auto_increment,intake date,constraint primary key(batchid));
create table user(uid varchar(100),degid int,batchid int,username varchar(100),password varchar(100),accountType varchar(20),constraint primary key(uid),constraint foreign key(degid) references degree(degid),constraint foreign key(batchid) references batch(batchid));	
create table evaluation_criteria_heading(echid int,text varchar(100),constraint primary key(echid));
create table evaluation_criteria(ecid int auto_increment,echid int,text varchar(100),constraint primary key(ecid),constraint foreign key(echid) references evaluation_criteria_heading(echid));
create table semester(semid int auto_increment,text varchar(100),constraint primary key(semid));
create table lecturer(lecid varchar(100),name varchar(100),constraint primary key(lecid));
create table subject(subid varchar(100),lecid varchar(100),degid int,semid int,title varchar(100),credits int,constraint primary key(subid),constraint foreign key(lecid) references lecturer(lecid),constraint foreign key(degid) references degree(degid),constraint foreign key(semid) references semester(semid));
create table marks(uid varchar(100),subid varchar(100),ecid int,dateOfSubmitted date,constraint primary key(uid,subid,dateOfSubmitted),constraint foreign key(uid) references user(uid),constraint foreign key(subid) references subject(subid),constraint foreign key(ecid) references evaluation_criteria(ecid));

INSERT INTO `studentfeedback`.`faculty`
(`name`)
VALUES
('Department of Computer Science');

INSERT INTO `studentfeedback`.`batch`
(`intake`)
VALUES
('2018-01-05'),('2019-01-05');

INSERT INTO `studentfeedback`.`degree`
(`facid`,`name`)
VALUES
(1,'BSc (Computer Science)'),(1,'BSc (Information Systems)');

INSERT INTO `studentfeedback`.`user`
(`uid`,
`degid`,
`batchid`,
`username`,
`password`,
`accountType`)
VALUES
('abc123',1,1,'Amal Silva','123','student'),('abc456',2,2,'Sunil Perera','456','student');

INSERT INTO `studentfeedback`.`evaluation_criteria_heading`
(`echid`,
`text`)
VALUES
(1,'Enthusiasm'),
(2,'Organization'),
(3,'Lecturer student interaction'),
(4,'Task Organization'),
(5,'Clarity'),
(6,'Learning Experiences');

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
(3,'Feedback provided for the students questions were understood'),
(4,'Speed of the lecturer was reasonable'),
(4,'Usage of teaching aid(PPT, video clips, white board, etc)'),
(4,'Usage of textbooks, websites, periodicals, etc. were recommended'),
(4,'Worked examples/tutorials were provided'),
(4,'Sufficient time was provided for tutorials/worked examples'),
(4,'Lecturer advised regarding examinations'),
(4,'Feedback on continous assignments/tutorials were helpful'),
(5,'Black/white board or powerpoint presentations were clear'),
(5,'Lecturer was sufficiently audible'),
(5,'Lecturer was clear when communicating with students'),
(6,'Lecture was good'),
(6,'Course content'),
(6,'Module/subject was understood');

INSERT INTO `studentfeedback`.`semester`
(`text`)
VALUES
('Year 1 / Semester 1'),('Year 1 / Semester 2'),('Year 2 / Semester 1'),('Year 2 / Semester 2');

INSERT INTO `studentfeedback`.`lecturer`
(`lecid`,
`name`)
VALUES
('L001','Kamal Perera'),('L002','Nimal Silva');

INSERT INTO `studentfeedback`.`subject`
(`subid`,
`lecid`,
`degid`,
`semid`,
`title`,
`credits`)
VALUES
('CSC 101','L001',1,1,'Progamming',3),('CSC 102','L002',1,1,'Database Management System',2),
('CSC 201','L001',1,2,'Progamming - 2',3),('CSC 202','L002',1,2,'Database Management System - 2',2),
('CSC 301','L001',1,3,'Progamming - 3',3),('CSC 302','L002',1,3,'Database Management System - 3',2),
('IS 101','L001',2,1,'Progamming',3),('IS 102','L002',2,1,'Web Development',2),
('IS 201','L001',2,2,'Progamming - 2',3),('IS 202','L002',2,2,'Web Development - 2',2),
('IS 301','L001',2,3,'Progamming - 3',3),('IS 302','L002',2,3,'Web Development - 3',2);

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

select f.name,d.name from user u,faculty f,degree d where f.facid=d.facid && d.degid=u.degid && u.uid='abc123';

select month(curdate())-month('2020-04-03');
