# spring-legacy-xml-setting
# h2 - mybatis

```shell script
CREATE TABLE MEMBER(
    NO INT PRIMARY KEY AUTO_INCREMENT,
    ID VARCHAR(255) UNIQUE,
    NAME VARCHAR(255),
    PASSWORD VARCHAR(255),
    CREATE_DATE TIMESTAMP DEFAULT SYSDATE
);
INSERT INTO MEMBER(ID,NAME,PASSWORD) VALUES('ADMIN','관리자','1234');
INSERT INTO MEMBER(ID,NAME,PASSWORD) VALUES('USER','유저','5678');
INSERT INTO MEMBER(ID,NAME,PASSWORD) VALUES('WOO','수수','1404');

CREATE TABLE POST(
    NO INT PRIMARY KEY AUTO_INCREMENT,
    WRITER VARCHAR(255),
    TITLE VARCHAR(255),
    CREATE_DATE TIMESTAMP DEFAULT SYSDATE
);
INSERT INTO POST(WRITER , TITLE ) VALUES('ADMIN', 'FIRST');
INSERT INTO POST(WRITER , TITLE ) VALUES('ADMIN', 'TWO');
INSERT INTO POST(WRITER , TITLE ) VALUES('ADMIN', 'THREE');
INSERT INTO POST(WRITER , TITLE ) VALUES('USER', 'WOO');
INSERT INTO POST(WRITER , TITLE ) VALUES('USER', 'PA');
INSERT INTO POST(WRITER , TITLE ) VALUES('WOO', 'RURU');
```