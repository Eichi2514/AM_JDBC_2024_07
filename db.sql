DROP DATABASE IF EXISTS AM_JDBC_2024_07;
CREATE DATABASE AM_JDBC_2024_07;
USE AM_JDBC_2024_07;

SHOW TABLES;

DROP TABLE article;
CREATE TABLE article (
                         id INT(10) UNSIGNED PRIMARY KEY AUTO_INCREMENT,
                         regDate DATETIME NOT NULL,
                         updateDate DATETIME NOT NULL,
                         title CHAR(100) NOT NULL,
                         loginId CHAR(100) NOT NULL,
                         `body` TEXT NOT NULL
);

SELECT *
FROM article;

SELECT id, regDate, updateDate, title, `body` FROM article;

SELECT NOW();

SELECT '제목1';

SELECT CONCAT('제목1',' 1');

SELECT SUBSTRING(RAND() * 1000 FROM 1 FOR 2);

INSERT INTO article
SET regDate = NOW(),
    updateDate = NOW(),
    title = CONCAT ('제목', SUBSTRING(RAND()*1000 FROM 1 FOR 2)),
    `body` = CONCAT ('내용', SUBSTRING(RAND()*1000 FROM 1 FOR 2));

UPDATE article
SET title = 'qwe',
    `body` = 'qwe',
    updateDate = NOW()
WHERE id = 2;

SELECT MAX(id)+1
FROM article;

SELECT id, regDate, updateDate, title, `body`, MAX(id)+1 FROM article ORDER BY id DESC;

CREATE TABLE `MEMBER` (
                          MEMBERNO INT PRIMARY KEY AUTO_INCREMENT,
                          REGDATE DATETIME,
                          USERID TEXT NOT NULL,
                          USERPW TEXT NOT NULL,
                          USERNAME TEXT NOT NULL
);

SELECT *
FROM `MEMBER`