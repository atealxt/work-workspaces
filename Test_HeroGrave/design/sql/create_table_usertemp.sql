#online user table

use herograve;
drop table if exists usertemp;

create table usertemp (
	user_id int(32) primary key,
	
	#where the user is.	
	path varchar(16) default "index.jsp",
	
	#last action time: HHMMSS
	last_action_time int(6),
	
	foreign key(user_id) references user(user_id)
);

select * from usertemp;