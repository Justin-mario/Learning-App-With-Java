create database if not exists learning_app_db;

create user if not exists 'super_user'@'localhost' identified by 'super123';
grant all privileges on learning_app_db.* to 'super_user'@'localhost';
flush privileges;