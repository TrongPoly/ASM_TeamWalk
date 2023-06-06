create database java4

use java4

create table Users (
	Id varchar(15) primary key not null,
	Passwords varchar(50) not null,
	Email varchar(50) not null,
	FullName nvarchar(50) not null,
	Admins bit not null,
)

create table Video (
	Id varchar(15) primary key not null,
	Title nvarchar(150) not null,
	Poster nvarchar(150) not null,
	Viewss int not null,
	Descriptions nvarchar(150) not null,
	Active bit not null
)

create table Favorite(
	Id int primary key not null,
	UserID varchar(15) references Users(Id) not null,
	VideoID varchar(15) references Video(Id) not null,
	Likedate date not null
)

create table Share(
	Id int primary key not null,
	UserID varchar(15) references Users(Id) not null,
	VideoID varchar(15) references Video(Id) not null,
	Emails varchar(50) not null,
	Sharedate date not null
)