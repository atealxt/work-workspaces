#user base info

use herograve;
drop table if exists user;

create table user (
	user_id int(32) primary key,
	user_name varchar(16) not null,
	user_level_id int(2) not null default 3,
	
	#notice that only number and english words. input length:6-16
	user_pwd varchar(24) not null,
	
	user_question varchar(32),
	user_question_answer varchar(32),
	
	email varchar(32),
	
	#register datetime
	#MySQL displays DATETIME values in 'YYYY-MM-DD HH:MM:SS' format
	user_regtime DATETIME not null,	
	
	foreign key(user_level_id) references userlevel(user_level_id)
);

insert into user values(0,"herograve",0,"tkySk2dHlDFHAw479+9udQ==","","","a@a.com","2008-06-20 15:47:00" );
insert into user values(1,"admin",1,"AZICOnu9cyUFFvBp3xi1AA==","","","a@a.com","2008-06-20 15:47:00" );
#insert into user values(2,"banzhu",2,"fqcgVp28SEcz7JE8WifG5w==","","","a@a.com","2008-06-20 15:47:00" );
insert into user values(3,"atealxt",3,"Ic2QsJVCwaXFfKc6eb7iuQ==","","","a@a.com","2008-06-27 15:47:00" );

select * from user;