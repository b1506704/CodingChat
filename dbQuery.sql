use CodingChat
create table ChatUser(
	username nvarchar(50) PRIMARY KEY,
	password nvarchar(50)	


)
select * from ChatUser where ChatUser.username='admin' and ChatUser.password='admin';
insert into ChatUser values ('tuan','tuan');