#tie zi content

#lou zhu de nei rong ye yao jia jin lai.

use herograve;
drop table if exists topicinfo;

create table topicinfo (
	topicinfo_id int(32) primary key,
	#topicinfo_floorcount varchar(128) not null default 0,
	topicinfo_text varchar(2048) not null,
	topictitle_id int(32) not null,
	
	replyuser_id int(32) not null,
	replytime DATETIME not null,
	
	foreign key(replyuser_id) references user(user_id),
	foreign key(topictitle_id) references topictitle(topictitle_id)	
);

insert into topicinfo values(0,"content first test!!",0,1,"2008-06-20 15:48:00");
insert into topicinfo values(1,"ok,this is reply!!",0,3,"2008-06-20 15:49:00");

select * from topicinfo;