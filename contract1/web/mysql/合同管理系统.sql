create database c1;
drop database c1;
use c1;
create table employee
(
    id int,
    name varchar(40),
    sex varchar(4),
    birthday date,
    entry_date date,
    job varchar(40),
    salary decimal(8,2),
    resume text
);
create table user
(
u_id int(30) not null primary key auto_increment,
u_name varchar(255),
u_password varchar(255),
u_mail varchar(255)
);
create table contract
(
c_id int(30) not null primary key auto_increment,
c_name varchar(255),
c_dtype varchar(255),
c_type varchar(255),
c_supplier varchar(255),
c_price varchar(255),
c_tprice varchar(255),
c_count int(30),
c_date varchar(255),
c_campus varchar(255),
c_person varchar(255),
c_upload varchar(255),
c_remark varchar(255)
);
create table device
(
d_id int(30) not null primary key auto_increment,
d_cid int(30),
d_did varchar(255),
d_type varchar(255),
d_campus varchar(255),
d_apartment varchar(255),
d_people varchar(255),
d_date varchar(255),
d_used varchar(255),
d_undate varchar(255),
d_remark varchar(1000)
);


