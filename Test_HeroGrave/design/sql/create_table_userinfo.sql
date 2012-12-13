#user detail info

use herograve;
drop table if exists userinfo;

create table userinfo (
	user_id int(32) primary key,
	#may be add user_name is better..
	
	#the user icon path
	iconpath varchar(48) default "picture/default/face/watermelon.jpg",

	#0-unknow 1-male 2-female
	sex int(1) not null default 0,	
	
	#topictitle count
	article int(16) not null default 0,
	
	#topicinfo	+1  topictitle+2
	score int(32) not null default 0,
	
	money int(32) not null default 100,
	
	#tou xian
	level int(2) not null default 1,
	
	#unit: hour 
	online_time int(16) not null default 0,
	#temporary online minutes,when >60 online_temp_m=0,online_time++
	#online_temp_m int(2) not null default 0,
	
	foreign key(user_id) references user(user_id),
	foreign key(level) references userlevelshow(levelshow_id)
);

insert into userinfo values(0,"picture/default/face/watermelon.jpg",1,0,0,100,1,0);
insert into userinfo values(1,"picture/default/face/watermelon.jpg",1,1,1,100,1,0);
insert into userinfo values(3,"picture/user/atealxt/face.png",0,1,1,100,1,0);


select * from userinfo;