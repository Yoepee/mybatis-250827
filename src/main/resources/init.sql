-- DROP DATABASE IF EXISTS mybatis_db;
-- CREATE DATABASE mybatis_db;
-- USE mybatis_db;

DROP TABLE IF EXISTS article;
CREATE TABLE article(
    id INT UNSIGNED NOT NULL AUTO_INCREMENT,
    createDate DATETIME NOT NULL,
    modifyDate DATETIME NOT NULL,
    title CHAR(100) NOT NULL,
    content TEXT NOT NULL,
    PRIMARY KEY(id)
);

INSERT INTO article(createDate, modifyDate, title, content)
VALUES
    (NOW(), NOW(), '제목 1', '내용 1'),
    (NOW(), NOW(), '제목 2', '내용 2');