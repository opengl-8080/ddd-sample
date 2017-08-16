-- ユーザ
CREATE TABLE USERS (
    ID       BIGINT NOT NULL IDENTITY,
    LOGIN_ID VARCHAR(20) NOT NULL,
    NAME     VARCHAR(20) NOT NULL,
    PASSWORD VARCHAR(60) NOT NULL,
    CONSTRAINT USERS_UK UNIQUE (LOGIN_ID)
);

INSERT INTO USERS (LOGIN_ID, NAME, PASSWORD)
VALUES ('admin', '管理者', '$2a$08$BFB5TUdWD4woNScz4G0gBOwAPUTJ3ew8l7HrNbI.Fuz7O.tPs2laS'); -- password > admin

-- 権限
CREATE TABLE AUTHORITIES (
    ID          BIGINT       NOT NULL IDENTITY,
    AUTHORITY   VARCHAR(64)  NOT NULL,
    DESCRIPTION VARCHAR(128) NOT NULL,
    CONSTRAINT AUTHORITIES_UK UNIQUE (AUTHORITY)
);

INSERT INTO AUTHORITIES (AUTHORITY, DESCRIPTION)
VALUES ('SYSTEM_MANAGER', 'システム管理者');
INSERT INTO AUTHORITIES (AUTHORITY, DESCRIPTION)
VALUES ('MASTER_MAINTENANCE_OPERATOR', 'マスタメンテ担当');
INSERT INTO AUTHORITIES (AUTHORITY, DESCRIPTION)
VALUES ('ORDER_OPERATOR', '注文担当');
INSERT INTO AUTHORITIES (AUTHORITY, DESCRIPTION)
VALUES ('SALES_OPERATOR', '営業担当');
INSERT INTO AUTHORITIES (AUTHORITY, DESCRIPTION)
VALUES ('PURCHASE_OPERATOR', '発注担当');
INSERT INTO AUTHORITIES (AUTHORITY, DESCRIPTION)
VALUES ('DELIVERY_OPERATOR', '出荷担当');

-- ユーザ権限
CREATE TABLE USER_AUTHORITIES (
    ID           BIGINT NOT NULL IDENTITY,
    USER_ID      BIGINT NOT NULL,
    AUTHORITY_ID BIGINT NOT NULL,
    CONSTRAINT USER_AUTHORITIES_UK UNIQUE (USER_ID, AUTHORITY_ID),
    CONSTRAINT USER_AUTHORITIES_FK1 FOREIGN KEY (USER_ID) REFERENCES USERS (ID),
    CONSTRAINT USER_AUTHORITIES_FK2 FOREIGN KEY (AUTHORITY_ID) REFERENCES AUTHORITIES (ID)
);

INSERT INTO USER_AUTHORITIES (USER_ID, AUTHORITY_ID)
VALUES (
    (SELECT ID FROM USERS WHERE LOGIN_ID = 'admin'),
    (SELECT ID FROM AUTHORITIES WHERE AUTHORITY = 'SYSTEM_MANAGER')
);
