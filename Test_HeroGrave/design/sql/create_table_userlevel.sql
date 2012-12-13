#user authority

use herograve;
drop table if exists userlevel;

create table userlevel (
	#0-owner 1-master 2-banzhu 3-member
	user_level_id int(2) primary key,
	
	user_level_name varchar(16) not null
);

insert into userlevel values(0,"owner");
insert into userlevel values(1,"master");
insert into userlevel values(2,"banzhu");
insert into userlevel values(3,"member");

select * from userlevel;