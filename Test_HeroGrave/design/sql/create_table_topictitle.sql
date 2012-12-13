#tie zi base info

use herograve;
drop table if exists topictitle;

create table topictitle (
	topictitle_id int(32) primary key,
	topictitle_title varchar(128) not null,
	topictitle_text varchar(2048) not null,
	topicarea_id int(32) not null,
	
	createtime DATETIME not null,	
	createuser_id int(32) not null,
	viewcount int(32) not null default 0,
	replycount int(32) not null default 0,
	
	#null which have no reply.
	latestreplytime DATETIME,
	latestreplyusername varchar(16),
	
	foreign key(createuser_id) references user(user_id),
	foreign key(topicarea_id) references topicarea(topicarea_id)
);

insert into topictitle values(0,"helloworld!","hello to the world!",20,"2008-06-20 15:47:00",1,3,2,"2008-06-20 15:49:00","atealxt");
insert into topictitle values(1,"tie zi 1!","i'm tie zi 1 oh yeah!",20,"2008-07-02 14:25:00",3,2,0,null,null);

select * from topictitle;