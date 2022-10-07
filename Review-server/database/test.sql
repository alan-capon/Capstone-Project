drop database if exists product_reviews_test;
create database product_reviews_test;
use product_reviews_test;

create table app_user (
	app_user_id int primary key auto_increment,
    username varchar(50) not null unique,
    first_name varchar(25) not null,
    last_name varchar(25) not null,
    email varchar(50) not null,
    password_hash varchar(2048) not null,
    `disabled` bit not null default(0)
);

create table app_role (
	app_role_id int primary key auto_increment,
    `name` varchar(50) not null unique
);

create table app_user_role (
	app_user_id int not null,
	app_role_id int not null,
    constraint pk_app_user_role
		primary key (app_user_id, app_role_id),
	constraint fk_app_user_role_app_user_id
		foreign key (app_user_id)
        references app_user(app_user_id),
	constraint fk_app_user_role_app_role_id
		foreign key (app_role_id)
        references app_role(app_role_id)
);

create table product (
	product_id int primary key auto_increment,
    `name` varchar(50) not null,
    `description` varchar(1000)
);

create table product_reviews (
	product_review_id int primary key auto_increment,
    app_user_id int not null,
    product_id int not null,
    `date` date not null,
    review varchar(1000) not null,
    constraint fk_product_reviews_app_user_id
		foreign key(app_user_id)
        references app_user(app_user_id),
	constraint fk_product_reviews_product_id
		foreign key (product_id)
        references product(product_id)
);

delimiter //
create procedure set_known_good_state()
begin

	delete from product_reviews;
    alter table product_reviews auto_increment = 1;
    delete from product;
	alter table product auto_increment = 1;

    insert into product(name, description)
	values
		('iPhone', 'cellphone'),
		('Sony Headphones', 'Noise cancelling headphones');

	insert into product_reviews(app_user_id, product_id, date, review)
	values
		(1, 1, '2022-05-22', 'Great phone and the camera is great'),
		(1, 2, '2022-09-22', 'The noise cancelling works great and has great sound');

end //
delimiter ;