DROP DATABASE ksca;
CREATE DATABASE ksca;
USE ksca;

CREATE TABLE category (
	code 		VARCHAR(10) NOT NULL,
	name 		VARCHAR(50) NOT NULL,
	par_cat 	VARCHAR(10) NOT NULL,
	par_name	VARCHAR(50) NOT NULL,
	PRIMARY KEY (code)
);

CREATE TABLE agency (
	code    INTEGER     NOT NULL AUTO_INCREMENT, 
	name    VARCHAR(50) NOT NULL, 
	manager VARCHAR(12) NULL,     
	tel     VARCHAR(30) NULL,     
	PRIMARY KEY (code)
);

CREATE TABLE program (
	code   INTEGER     NOT NULL AUTO_INCREMENT,
	name   VARCHAR(50) NOT NULL,
	cat    VARCHAR(10) NOT NULL,
	agency INTEGER     NOT NULL,
	PRIMARY KEY (code),
	FOREIGN KEY (agency)
		REFERENCES agency (code) ON DELETE CASCADE,
	FOREIGN KEY (cat)
		REFERENCES category (code) ON DELETE CASCADE
);

CREATE TABLE manager (
	id         VARCHAR(50) NOT NULL, 
	password   VARCHAR(20) NOT NULL, 
	name       VARCHAR(12) NOT NULL, 
	tel        VARCHAR(30) NOT NULL, 
	mail       VARCHAR(40) NOT NULL, 
	is_approve BOOLEAN     DEFAULT FALSE,
	is_exist   BOOLEAN     DEFAULT TRUE,
	permission ENUM('MASTER','MANAGER') DEFAULT 'MANAGER', 
	PRIMARY KEY (id)
);

CREATE TABLE area (
	code        VARCHAR(20) NOT NULL,
	manager		VARCHAR(50)	NOT NULL,
	city        VARCHAR(20) NOT NULL,
	city_code   VARCHAR(5) 	NOT NULL,
	gu          VARCHAR(20) NOT NULL,
	gu_code     VARCHAR(5)  NOT NULL,
	branch      VARCHAR(50) NOT NULL,
	branch_code VARCHAR(5)  NOT NULL,
	PRIMARY KEY (code),
	FOREIGN KEY (manager)
		REFERENCES manager (id) ON DELETE CASCADE
);

CREATE TABLE scc (
	code      VARCHAR(15)  NOT NULL,
	dong      VARCHAR(50)  NULL,
	name      VARCHAR(50)  NOT NULL,
	address   VARCHAR(255) NULL,
	reg_date  DATE         NULL,
	site      FLOAT        NULL,
	building  FLOAT        NULL,
	member    INTEGER      NULL,
	male      INTEGER      NULL,
	female    INTEGER      NULL,
	own       VARCHAR(10)  NULL,
	tel       VARCHAR(30)  NULL,
	president VARCHAR(12)  NULL,
	phone     VARCHAR(30)  NULL,
	manager   VARCHAR(50)  NOT NULL,
	area      VARCHAR(20)  NOT NULL,
	PRIMARY KEY (code),
	FOREIGN KEY (manager)
		REFERENCES manager (id) ON DELETE CASCADE
);

CREATE TABLE schedule (
	code         INTEGER     NOT NULL AUTO_INCREMENT, 
	manager		 VARCHAR(50) NOT NULL,
	ssc          VARCHAR(15) NOT NULL, 
	program      INTEGER     NOT NULL, 
	begin_date   DATE        NOT NULL, 
	end_date     DATE        NOT NULL, 
	time		 TIME        NULL,
	monthly_oper INTEGER     NOT NULL, 
	active_user  INTEGER     NULL,
	PRIMARY KEY (code),
	FOREIGN KEY (ssc)
		REFERENCES scc (code) ON DELETE CASCADE,
	FOREIGN KEY (program)
		REFERENCES program (code) ON DELETE CASCADE,
	FOREIGN KEY (manager)
		REFERENCES manager (id) ON DELETE CASCADE
);