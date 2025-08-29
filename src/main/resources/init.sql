-- DROP DATABASE IF EXISTS db_dev;
-- CREATE DATABASE db_dev;
-- USE db_dev;

DROP TABLE IF EXISTS post;
CREATE TABLE post(
    id INT UNSIGNED NOT NULL AUTO_INCREMENT,
    title CHAR(100) NOT NULL,
    content TEXT NOT NULL,
    memberId INT UNSIGNED NOT NULL,
    createDate DATETIME NOT NULL,
    modifyDate DATETIME NOT NULL,
    PRIMARY KEY(id)
);

INSERT INTO post(createDate, modifyDate, title, content, memberId)
VALUES
    (NOW(), NOW(), '제목 1', '내용 1', 1);

INSERT INTO post(createDate, modifyDate, title, content, memberId)
VALUES
    (NOW(), NOW(), '제목 2', '내용 2', 2);

DROP TABLE IF EXISTS `member`;
CREATE TABLE `member`(
                     id INT UNSIGNED NOT NULL AUTO_INCREMENT,
                     createDate DATETIME NOT NULL,
                     modifyDate DATETIME NOT NULL,
                     username CHAR(100) NOT NULL UNIQUE,
                     `password` CHAR(100) NOT NULL,
                     `name` CHAR(100) NOT NULL,
                     `email` CHAR(100) NOT NULL,
                     PRIMARY KEY(id)
);

INSERT INTO `member`(createDate, modifyDate, username, password, name, email)
VALUES
    (NOW(), NOW(), 'user1', '{noop}1234', '유저1', 'user1@test.com');

INSERT INTO `member`(createDate, modifyDate, username, password, name, email)
VALUES
    (NOW(), NOW(), 'user2', '{noop}1234', '유저2', 'user2@test.com');