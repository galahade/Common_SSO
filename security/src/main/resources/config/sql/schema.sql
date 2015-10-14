drop table customer if exists;
drop table group_role if exists;
drop table rgroup if exists;
drop table role if exists;
drop table user_group if exists;
create table customer (id bigint generated by default as identity, language_id integer not null, last_visit_time timestamp, currency varchar(255) not null, expired boolean not null, password varchar(255) not null, social_account_id varchar(255), status integer not null, type varchar(255) not null, user_name varchar(255) not null, primary key (id),unique key (user_name));
create table group_role (group_id bigint not null, role_id bigint not null, primary key (group_id, role_id));
create table rgroup (id bigint generated by default as identity, group_name varchar(255) not null, primary key (id));
create table role (id bigint generated by default as identity, role_name varchar(255) not null, primary key (id));
create table user_group (user_id bigint not null, group_id bigint not null, primary key (user_id, group_id));
alter table group_role add constraint FK_t82eprllffbdyqkqvw0k6vbvl foreign key (role_id) references role;
alter table group_role add constraint FK_r1117uolaagpjdqtsp72qem50 foreign key (group_id) references rgroup;
alter table user_group add constraint FK_789v3ejjs9uj5vu1ufexj2tbv foreign key (group_id) references rgroup;
alter table user_group add constraint FK_mps81qti05d9he0y66y5jt845 foreign key (user_id) references customer;