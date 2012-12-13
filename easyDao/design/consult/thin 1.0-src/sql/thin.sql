--mysql

create database thin;

use thin;

drop table z_user;

CREATE TABLE z_user (
	userid VARCHAR(255) NOT NULL,
	password VARCHAR(255),
	username VARCHAR(255),
	age int,
	birthday date,
	group_id varchar(255),
	role_id VARCHAR(255),
	PRIMARY KEY (userid)
);

drop table z_group;
create table z_group (
	group_id varchar(255),
	group_name varchar(255),
	primary key(group_id)
);

insert into z_group values('651080001','总部');
insert into z_group values('651080002','网点');
insert into z_group values('651080003','分行');




select a.*,b.* from z_user a,z_group b where a.group_id=b.group_id ;

select z_user.*,z_group.* from z_user  left join z_group  on z_user.group_id=z_group.group_id ;

select a.*,b.* from z_user a right join z_group b on a.group_id=b.group_id ;

SELECT z_user.BIRTHDAY,z_user.AGE,z_user.USERID,z_user.ROLE_ID,z_user.GROUP_ID,z_user.USERNAME,z_user.PASSWORD,z_group.GROUP_NAME,z_group.GROUP_ID 
FROM z_user LEFT JOIN z_group ON z_user.group_id=z_group.group_id   













