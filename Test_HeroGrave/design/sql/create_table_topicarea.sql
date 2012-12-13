#bbs zhuan qu

use herograve;
drop table if exists topicarea;

create table topicarea (
	topicarea_id int(32) primary key,
	topicarea_name varchar(16) not null,
	
	#0: userlevel 0-2 can enter.
	#1: loged in user can enter.
	#2: everybody can enter.
	topicarea_level int(1) not null
);

insert into topicarea values(0,"management",0);
insert into topicarea values(5,"The Tempest",1);
insert into topicarea values(19,"game",2);
insert into topicarea values(20,"water",2);

select * from topicarea;