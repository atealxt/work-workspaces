create database book

CREATE TABLE booktype(
	id int(11) NOT NULL auto_increment,
	title varchar(100),
	detail varchar(100), 
	PRIMARY KEY  (id)
); 

CREATE TABLE book(
	id int(11) NOT NULL auto_increment,
	book_name varchar(100),
	author varchar(100),
	type_id int,
	price float,
	brief varchar(1000),
	PRIMARY KEY  (id)
); 







