DROP DATABASE ksca;
CREATE DATABASE ksca;
USE ksca;

CREATE TABLE cat1 (
	code 		VARCHAR(10) NOT NULL,
	name 		VARCHAR(50) NOT NULL,
	PRIMARY KEY (code)
);

CREATE TABLE cat2 (
	code 		VARCHAR(10) NOT NULL,
	name 		VARCHAR(50) NOT NULL,
	cat1 		VARCHAR(10) NOT NULL,
	PRIMARY KEY (code, cat1),
	FOREIGN KEY (cat1)
		REFERENCES cat1 (code) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE area (
	code        VARCHAR(20) NOT NULL,
	city        VARCHAR(20) NOT NULL,
	city_code   VARCHAR(5) 	NOT NULL,
	gu          VARCHAR(20) NOT NULL,
	gu_code     VARCHAR(5)  NOT NULL,
	PRIMARY KEY (code)
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
	area       VARCHAR(20) NOT NULL,
	PRIMARY KEY (id),
	FOREIGN KEY (area)
		REFERENCES area (code) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE branch (
	area_code	VARCHAR(20) NOT NULL,
	branch_code VARCHAR(10) NOT NULL,	
	branch      VARCHAR(50) NOT NULL,
	PRIMARY KEY (area_code, branch_code),
	FOREIGN KEY (area_code)
		REFERENCES area (code) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE scc (
	area_code   VARCHAR(20)  NOT NULL,
	branch_code VARCHAR(10)  NOT NULL,	
	scc_code    VARCHAR(10)  NOT NULL,
	dong      	VARCHAR(50)  NULL,
	name      	VARCHAR(50)  NOT NULL,
	address   	VARCHAR(255) NULL,
	reg_date  	DATE         NULL,
	site      	FLOAT        NULL,
	building  	FLOAT        NULL,
	member    	INTEGER      NULL,
	male      	INTEGER      NULL,
	female   	INTEGER      NULL,
	own      	VARCHAR(10)  NULL,
	tel      	VARCHAR(30)  NULL,
	president   VARCHAR(12)  NULL,
	phone    	VARCHAR(30)  NULL,
	PRIMARY KEY (area_code, branch_code, scc_code),
	FOREIGN KEY (area_code, branch_code)
		REFERENCES branch (area_code, branch_code) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE agency (
	code    INTEGER     NOT NULL AUTO_INCREMENT,
	area    VARCHAR(20) NOT NULL,
	name    VARCHAR(50) NOT NULL, 
	manager VARCHAR(12) NULL,     
	tel     VARCHAR(30) NULL,     
	PRIMARY KEY (code),
	FOREIGN KEY (area)
		REFERENCES area (code) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE program (
	code   INTEGER     NOT NULL AUTO_INCREMENT,
	area   VARCHAR(20) NOT NULL,
	name   VARCHAR(50) NOT NULL,
	cat1   VARCHAR(10) NOT NULL,
	cat2   VARCHAR(10) NOT NULL,
	agency INTEGER     NOT NULL,
	PRIMARY KEY (code),
	FOREIGN KEY (agency)
		REFERENCES agency (code) ON DELETE CASCADE ON UPDATE CASCADE,
	FOREIGN KEY (cat1, cat2)
		REFERENCES cat2 (cat1, code) ON DELETE CASCADE ON UPDATE CASCADE,
	FOREIGN KEY (area)
		REFERENCES area (code) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE offer (
	code         INTEGER     NOT NULL AUTO_INCREMENT, 
	area_code    VARCHAR(20) NOT NULL,
	branch_code	 VARCHAR(10) NOT NULL,	
	ssc_code	 VARCHAR(10) NOT NULL,
	program      INTEGER     NOT NULL, 
	begin_date   DATE        NOT NULL, 
	end_date     DATE        NOT NULL, 
	monthly_oper INTEGER     NOT NULL, 
	active_user  INTEGER     NULL,
	color		 VARCHAR(20) NOT NULL,
	PRIMARY KEY (code),
	FOREIGN KEY (area_code, branch_code, ssc_code)
		REFERENCES scc (area_code, branch_code, scc_code) ON DELETE CASCADE ON UPDATE CASCADE,
	FOREIGN KEY (program)
		REFERENCES program (code) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE schedule (
	code         INTEGER     NOT NULL AUTO_INCREMENT,
	offer		 INTEGER	 NOT NULL,
	date		 DATE	     NOT NULL,
	PRIMARY KEY (code),
	FOREIGN KEY (offer)
		REFERENCES offer (code) ON DELETE CASCADE ON UPDATE CASCADE
);