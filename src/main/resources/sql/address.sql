
create table address(
id bigint(20) primary key auto_increment,
user_id bigint(20) not null,
addr varchar(50) default '' not null
);

insert into address (id,user_id, addr) values
(1,1,'北京'),
(2,1,'上海'),
(3,2,'黑河'),
(4,3,'哈尔滨');
