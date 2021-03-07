create table if not exists Users (
  id identity,
  createdAt timestamp not null,
  name varchar(30) not null,
  email varchar(30) not null
);

create table if not exists Scholarships (
  id varchar(4) not null,
  takePlace timestamp not null,
  codeName varchar(30) not null,
  scholarshipName varchar(30) not null,
  description varchar(60) not null
);

create table if not exists Registrations (
  registrationId identity,
  placedAt timestamp not null,
  userId varchar(4) not null,
  scholarshipId varchar(4) not null
);

alter table Registrations
    add foreign key (userId) references Users(id);
alter table Registrations
    add foreign key (scholarshipId) references Scholarships(id);
