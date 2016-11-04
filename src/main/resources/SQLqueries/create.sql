DROP TABLE IF EXISTS USERS;
DROP TABLE IF EXISTS GAME_RECORDS;
DROP TABLE IF EXISTS GAME_MOVES;

CREATE TABLE USERS (
  ID       BIGINT       NOT NULL AUTO_INCREMENT,
  SSO_ID   VARCHAR(30)  NOT NULL UNIQUE,
  PASSWORD VARCHAR(100) NOT NULL,
  NICKNAME VARCHAR(30)  NOT NULL,
  EMAIL    VARCHAR(50)  NOT NULL UNIQUE,
  PRIMARY KEY (ID)
);

CREATE TABLE GAME_RECORDS (
  ID            BIGINT   NOT NULL AUTO_INCREMENT,
  PLAYER_ONE_ID BIGINT   NOT NULL,
  PLAYER_TWO_ID BIGINT   NOT NULL,
  DATETIME      DATETIME NOT NULL,
  PRIMARY KEY (ID),
  FOREIGN KEY (PLAYER_ONE_ID) REFERENCES USERS (ID),
  FOREIGN KEY (PLAYER_TWO_ID) REFERENCES USERS (ID)
);

CREATE TABLE GAME_MOVES (
  ID       BIGINT     NOT NULL AUTO_INCREMENT,
  FIELD    VARCHAR(5) NOT NULL,
  SYMBOL   VARCHAR(1) NOT NULL,
  GAME_ID  BIGINT     NOT NULL,
  DATETIME DATETIME   NOT NULL,
  PRIMARY KEY (ID),
  FOREIGN KEY (GAME_ID) REFERENCES GAME_RECORDS (ID)
);