drop database if exists product_reviews;
create database product_reviews;
use product_reviews;

create table app_user (
	app_user_id int primary key auto_increment,
    username varchar(255) not null unique,
    password_hash varchar(2048) not null,
    `disable` bit not null default(0)
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
    `name` varchar(255) not null,
    `description` varchar(1000)
);

create table product_reviews (
	product_review_id int primary key auto_increment,
    app_user_id int not null,
    product_id int not null,
    `date` date not null,
    review varchar(1000) not null,
    constraint fk_product_reviews_app_user_id
		foreign key (app_user_id)
        references app_user(app_user_id),
	constraint fk_product_reviews_product_id
		foreign key (product_id)
        references product(product_id)
);