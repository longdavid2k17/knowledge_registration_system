delete from Users;
delete from Scholarships;
insert into Scholarships (id, takePlace, codeName, scholarshipName,description)
                values ('0', '2021-03-01', 'spring','Spring Framework w praktyce','Poznaj podstawy Springa');
insert into Scholarships (id, takePlace, codeName,scholarshipName,description)
                values ('1', '2021-04-01', 'algorithms','Algorytmy w JAVA','Poznaj skomplikowane algorytmy');
insert into Scholarships (id, takePlace, codeName,scholarshipName,description)
                values ('2', '2021-05-01', 'htmlcss','HTML/CSS w 2021','Poznaj podstawy HTML i stylizowania ');
insert into Scholarships (id, takePlace, codeName,scholarshipName,description)
                values ('3', '2021-06-01', 'mysql','MySQL w praktyce','Poznaj podstawy baz danych');
insert into Scholarships (id, takePlace, codeName,scholarshipName,description)
                values ('4', '2021-07-01', 'patterns','Wzorce projektowe','Poznaj podstawy wzorców projektowych');
insert into Scholarships (id, takePlace, codeName,scholarshipName,description)
                values ('5', '2021-08-01', 'hibernate','Hibernate w praktyce','Poznaj podstawy narzędzia Hibernate');

insert into Users (id, createdAt, name,email)
values ('0', '2021-08-01', 'Dawid','legerity@ebd68.com');
insert into Users (id, createdAt, name,email)
values ('1', '2021-08-01', 'Adam','legerity1@ebd68.com');
insert into Users (id, createdAt, name,email)
values ('2', '2021-08-01', 'Maciek','legerity2@ebd68.com');
insert into Users (id, createdAt, name,email)
values ('3', '2021-08-01', 'Tomek','legerity3@ebd68.com');
insert into Users (id, createdAt, name,email)
values ('4', '2021-08-01', 'Benio','legerity4@ebd68.com');
insert into Users (id, createdAt, name,email)
values ('5', '2021-08-01', 'henio','legerity5@ebd68.com');

insert into Registrations (registrationId, placedAt, userId,scholarshipId)
values ('0', '2021-08-01', '0','1');
insert into Registrations (registrationId, placedAt, userId,scholarshipId)
values ('1', '2021-08-01', '1','1');
insert into Registrations (registrationId, placedAt, userId,scholarshipId)
values ('2', '2021-08-01', '2','1');
insert into Registrations (registrationId, placedAt, userId,scholarshipId)
values ('3', '2021-08-01', '3','2');
insert into Registrations (registrationId, placedAt, userId,scholarshipId)
values ('4', '2021-08-01', '4','2');
insert into Registrations (registrationId, placedAt, userId,scholarshipId)
values ('5', '2021-08-01', '5','2');
