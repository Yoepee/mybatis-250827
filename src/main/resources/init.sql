-- DROP DATABASE IF EXISTS db_dev;
-- CREATE DATABASE db_dev;
-- USE db_dev;

DROP TABLE IF EXISTS post;
CREATE TABLE post(
    id INT UNSIGNED NOT NULL AUTO_INCREMENT,
    createDate DATETIME NOT NULL,
    modifyDate DATETIME NOT NULL,
    title CHAR(100) NOT NULL,
    content TEXT NOT NULL,
    PRIMARY KEY(id)
);

INSERT INTO post(createDate, modifyDate, title, content)
VALUES
    (NOW(), NOW(), '제목 1', '내용 1'),
    (NOW(), NOW(), '제목 2', '내용 2');