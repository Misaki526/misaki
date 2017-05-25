drop database if exists misaki;
create database mybatis CHARACTER SET UTF8;
use misaki;

/* 常见问题 */
create table m_faq
(
	faq_id varchar(36) primary key,
	faq_title varchar(100) not null,
	faq_answer text not null,
	faq_type varchar(30),
	faq_comment varchar(255)
);

insert into m_faq values("asdfghjkl", "aa", "aa", "desc", "aa");
insert into m_faq values("ff", "ff", "ff", "desc", "aa");
insert into m_faq values("gg", "gg", "gg", "desc", "aa");
insert into m_faq values("hh", "hh", "hh", "desc", "aa");
insert into m_faq values("ll", "ll", "ll", "desc", "aa");
insert into m_faq values("mm", "mm", "mm", "desc", "aa");
insert into m_faq values("nn", "nn", "nn", "desc", "aa");

select * from m_faq;

/* 用户 */
create table m_user
(
   user_id varchar(36) primary key,
   user_account varchar(20),
   user_name varchar(20),
   user_pass varchar(20)
);

insert into m_user values("12345", "admin", "admin", "admin");
insert into m_user values("23456", "user1", "user1", "123");
select * from m_user;

/* 保姆 */
create table m_nurse
(
	nurse_id varchar(36) primary key,
	nurse_name varchar(20),
	nurse_sex varchar(2),
	nurse_headImg varchar(100),
	nurse_salary decimal(12,2), 
	nurse_mobile varchar(20),
	nurse_score decimal(4,2),
	nurse_education varchar(20),
	nurse_birthday date,
	nurse_experience varchar(20),
	nurse_area varchar(20),
	last_modified datetime
);

insert into m_nurse values("12345", "李红利", "女", "", 4500, "15863548524", 
							0.05, "初中", "1982-02-02", "6年", "陕西", "2017-04-13 21:15:00");
insert into m_nurse values("23456", "耿燕红", "女", "", 4000, "13548542202", 
							0.1, "初中", "1969-03-03", "3年", "山东", "2017-04-13 21:15:00");
insert into m_nurse values("34567", "赵春红", "女", "", 4000, "18960005100", 
							0, "高中", "1978-01-18", "11月", "黑龙江", "2017-04-13 21:15:00");
insert into m_nurse values("45678", "高颖超", "女", "", 3500, "17102514000", 
							0, "大专", "1967-08-16", "10年", "陕西", "2017-04-13 21:15:00");
insert into m_nurse values("56789", "董英娟", "女", "", 6000, "13251200125", 
							0, "初中", "1981-11-07", "1年", "山西", "2017-04-13 21:15:00");
select * from m_nurse;
