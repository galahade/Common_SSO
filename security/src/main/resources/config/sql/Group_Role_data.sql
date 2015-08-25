insert into role (id, role_name) values (1, 'Seller');
insert into role (id, role_name) values (2, 'Buyer');
insert into rgroup (id, group_name) values (1, 'customer_group');
insert into group_role (group_id, role_id) values (1, 1);
insert into group_role (group_id, role_id) values (1, 2);