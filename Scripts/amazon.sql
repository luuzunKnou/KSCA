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
	password   VARCHAR(35) NOT NULL, 
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

CREATE TABLE offerprogram (
	code         INTEGER     NOT NULL AUTO_INCREMENT, 
	program      INTEGER     NOT NULL,
	reg_month	 DATE        NOT NULL,
	begin_date   DATE        NOT NULL, 
	end_date     DATE        NOT NULL, 
	color		 VARCHAR(20) NOT NULL,
	PRIMARY KEY (code),
	FOREIGN KEY (program)
		REFERENCES program (code) ON DELETE CASCADE ON UPDATE CASCADE
);


CREATE TABLE offer (
	code         INTEGER     NOT NULL AUTO_INCREMENT, 
	area_code    VARCHAR(20) NOT NULL,
	branch_code	 VARCHAR(10) NOT NULL,	
	scc_code	 VARCHAR(10) NOT NULL,
	program      INTEGER     NOT NULL,
	monthly_oper INTEGER     NOT NULL, 
	active_user  INTEGER     NULL,
	PRIMARY KEY (code),
	FOREIGN KEY (area_code, branch_code, scc_code)
		REFERENCES scc (area_code, branch_code, scc_code) ON DELETE CASCADE ON UPDATE CASCADE,
	FOREIGN KEY (program)
		REFERENCES offerprogram (code) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE schedule (
	code         INTEGER     NOT NULL AUTO_INCREMENT,
	offer		 INTEGER	 NOT NULL,
	date		 DATE	     NOT NULL,
	PRIMARY KEY (code),
	FOREIGN KEY (offer)
		REFERENCES offer (code) ON DELETE CASCADE ON UPDATE CASCADE
);



DELIMITER $$
DROP TRIGGER IF EXISTS inc_monthly_oper $$
CREATE TRIGGER inc_monthly_oper
AFTER INSERT ON schedule 
FOR EACH ROW 
BEGIN
	UPDATE offer 
		SET monthly_oper = monthly_oper + 1 
		WHERE code = NEW.offer;
END $$
DELIMITER ;

DELIMITER $$
DROP TRIGGER IF EXISTS dec_monthly_oper $$
CREATE TRIGGER dec_monthly_oper
AFTER DELETE ON schedule 
FOR EACH ROW 
BEGIN
	UPDATE offer 
		SET monthly_oper = monthly_oper - 1 
		WHERE code = OLD.offer;
END $$
DELIMITER ;


-- code name par_cat par_name 
INSERT INTO cat1(code, name) VALUES
	('3001','건강운동분야'),
	('3002','건강관리분야'),
	('3003','교육ㆍ상담분야'),
	('3004','여가분야'),
	('3005','권익증진분야'),
	('3006','사회참여분야'),
	('3007','공동작업장');

-- code name parents_cat 
INSERT INTO cat2(code, name, cat1) VALUES
	('101',' 체조 ㆍ댄스교실',			'3001'),
	('102',' 웃음교실',					'3001'),
	('103',' 요가ㆍ명상',				'3001'),
	('104',' 건강운동',					'3001'),
	('101',' 건강검진관련',				'3002'),
	('102',' 한방치료',					'3002'),
	('103',' 안마교실',					'3002'),
	('104',' 방문간호',					'3002'),
	('101',' 정보화교육',				'3003'),
	('102',' 언어교육',					'3003'),
	('103',' 인식개선교육',				'3003'),
	('104',' 에너지교육',				'3003'),
	('105',' 노인상담',					'3003'),
	('106',' 생활ㆍ안전교육',			'3003'),
	('107',' 프로그램발표대회',			'3003'),
	('101',' 음악활동',					'3004'),
	('102',' 바둑ㆍ장기교실',			'3004'),
	('103',' 문학활동',					'3004'),
	('104',' 미술활동',					'3004'),
	('105',' 문화ㆍ공연활동',			'3004'),
	('101',' 노인성교육',				'3005'),
	('102',' 소비자피해예방교육',		'3005'),
	('103',' 노인자살ㆍ학대예방교육',	'3005'),
	('101',' 방문 이ㆍ미용서비스',		'3006'),
	('102',' 시설 방문 위문공연',		'3006'),
	('103',' 자원봉사활동',				'3006'),
	('101',' 공동작업장',				'3007');

	
-- code city city_code gu gu_code branch branch_code
INSERT INTO area(code, city, city_code, gu, gu_code) VALUES
	('03-01', '대구', '03', '중구', '01');

-- id password name tel mail is_approve permission area
INSERT INTO manager(id, password, name, tel, mail, is_approve, permission, area) VALUES 
	('mcmoto',md5('rmawn586!'),'우금주','010-9004-0726','mcmoto@naver.com',true,'MASTER','03-01');

INSERT INTO branch(area_code, branch_code, branch) VALUES
	('03-01', '99', '없음');



