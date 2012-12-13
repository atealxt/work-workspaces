#user level(tou xian)

use herograve;
drop table if exists userlevelshow;

create table userlevelshow (
	levelshow_id int(2) primary key default 1,
	levelshow_name varchar(16) not null
);

insert into userlevelshow values(1,"farmer");
insert into userlevelshow values(10,"king");

select * from userlevelshow;