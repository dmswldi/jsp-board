CREATE TABLE member (
    memberid VARCHAR2(50) PRIMARY KEY,
    name VARCHAR2(50) NOT NULL,
    password VARCHAR2(50) NOT NULL,
    regdate DATE NOT NULL
);

SELECT * FROM member;
commit;

DROP TABLE article;
CREATE TABLE article (
    article_no NUMBER GENERATED AS IDENTITY,
    writer_id VARCHAR2(50) NOT NULL,
    writer_name VARCHAR2(50) NOT NULL,
    title VARCHAR2(255) NOT NULL,
    regdate DATE NOT NULL,
    moddate DATE NOT NULL,
    read_cnt NUMBER,
    PRIMARY KEY (article_no)
);

DROP TABLE article_content;
CREATE TABLE article_content (
    article_no NUMBER PRIMARY KEY,
    content VARCHAR2(4000)
);

SELECT * FROM article; -- 얜 왜 순서가 ? ???
-- 게시글 보기 수 제한 : TRICK
SELECT * FROM (
SELECT article_no, title, ROW_NUMBER() OVER (ORDER BY article_no DESC) rn
FROM article
)
WHERE rn BETWEEN 1 AND 5;

SELECT * FROM article_content;
DELETE article;

SELECT COUNT(*) FROM article;
SELECT COUNT(title) FROM article;-- 해당 칼럼의 null이 아닌 row 수

INSERT INTO article (writer_id, writer_name, title, regdate, moddate, read_cnt)
VALUES ('writer', 'id', 'name', sysdate, sysdate, 0);



CREATE TABLE reply (
    replyid NUMBER GENERATED AS IDENTITY,
    memberid VARCHAR2(50) NOT NULL,
    article_no NUMBER NOT NULL,
    body VARCHAR2(1000) NOT NULL,
    regdate DATE NOT NULL,
    PRIMARY KEY (replyid)
);
INSERT INTO reply (memberid, article_no, body, regdate)
VALUES (' ', 0, ' ', SYSDATE);


SELECT * FROM reply;

SELECT replyid, memberid, article_no, body, regdate
FROM reply
WHERE article_no=103
ORDER BY replyid DESC;

SELECT * FROM reply;
UPDATE reply
SET body = 'hello'
WHERE replyid = 16;
DELETE reply
WHERE replyid = 16;