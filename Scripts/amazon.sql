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
	('03-01', '대구', '03', '중구', '01'),
	('00-00', '테스트', '00', '테스트', '00');

-- id password name tel mail is_approve permission area
INSERT INTO manager(id, password, name, tel, mail, is_approve, permission, area) VALUES 
	('mcmoto',md5('mcmoto.ksca'),'우금주','010-9004-0726','mcmoto@naver.com',true,'MASTER','03-01'),
	('luuzun',md5('luuzun.ksca'),'이원준','010-4940-5498','luuzun@naver.com',true,'MASTER','00-00');

INSERT INTO branch(area_code, branch_code, branch) VALUES
	('03-01', '99', '없음'),
	('00-00', '99', '없음'),
	('00-00', '00', '테스트');

-- area_code branch_code scc_code dong name address reg_date site building member male female own tel president phone manager area      
INSERT INTO scc(area_code, branch_code, scc_code, dong, name, address, reg_date, site, building, member, male, female, own, tel, president, phone) VALUES 
	('00-00','99','001','남산동','반월당삼정그린코아   ','국채보상로 679-13','1997.04.29','224.80','220.4',28,10,18,'공설','053-421-5222','김혜수','010-5135-4777'),
	('00-00','99','002','남산동','남산그린빌'			,'봉산문화길1-43'   ,'1998.04.29','120.40','120.4',38,20,18,'사설','053-421-5222','박성광','010-5135-4777'),
	('00-00','99','003','동인동','동인시티'  			,'달성로21길 44-133','1999.04.29','350.20','345.4',48,30,18,'공설','053-421-5222','장동건','010-5135-4777'),
	('00-00','99','004','동인동','건들바위'  			,'달구벌대로 1975'  ,'2000.04.29','100.80','98.4' ,58,40,18,'사설','053-421-5222','김동현','010-5135-4777'),
	('00-00','99','005','동성동','동성1가'   			,'중앙대로62길 30-3','2001.04.29','214.50','211.4',68,50,18,'공설','053-421-5222','이상혁','010-5135-4777'),
	('00-00','99','006','달성동','달성2가'   			,'명륜로6길 5'      ,'2002.04.29','124.10','100.4',78,60,18,'사설','053-421-5222','우혜미','010-5135-4777'),
	('00-00','99','007','달성동','달성1가'  		 	,'달성로 2246'      ,'2003.04.29','124.10','100.4',78,60,18,'사설','053-421-5222','우혜미','010-5135-4777'),
	('00-00','99','008','남산동','반월당삼정그린코아'	,'국채보상로 679-13','1997.04.29','224.80','220.4',28,10,18,'공설','053-421-5222','김혜수','010-5135-4777'),
	('00-00','99','009','남산동','보성황실'	 			,'봉산문화길1-43'   ,'1998.04.29','120.40','120.4',38,20,18,'사설','053-421-5222','박성광','010-5135-4777'),
	('00-00','99','010','동인동','극동스타클래스'  	 	,'달성로21길 44-133','1999.04.29','350.20','345.4',48,30,18,'공설','053-421-5222','장동건','010-5135-4777'),
	('00-00','99','011','동인동','남산그린타운'  	 	,'달구벌대로 1975'  ,'2000.04.29','100.80','98.4' ,58,40,18,'사설','053-421-5222','김동현','010-5135-4777'),
	('00-00','99','012','동성동','효성해링턴'			,'중앙대로62길 30-3','2001.04.29','214.50','211.4',68,50,18,'공설','053-421-5222','이상혁','010-5135-4777'),
	('00-00','99','013','달성동','남문'   	 			,'명륜로6길 5'      ,'2002.04.29','124.10','100.4',78,60,18,'사설','053-421-5222','우혜미','010-5135-4777'),
	('00-00','99','014','달성동','달성1가'   			,'달성로 2246'      ,'2003.04.29','124.10','100.4',78,60,18,'사설','053-421-5222','우혜미','010-5135-4777'),
	('00-00','99','015','달성동','달성1가'   			,'달성로 2246'      ,'2003.04.29','124.10','100.4',78,60,18,'사설','053-421-5222','우혜미','010-5135-4777'),
	('00-00','99','016','남산동','까치아파트'			,'국채보상로 679-13','1997.04.29','224.80','220.4',28,10,18,'공설','053-421-5222','김혜수','010-5135-4777'),
	('00-00','99','017','남산동','남산그린빌'			,'봉산문화길1-43'   ,'1998.04.29','120.40','120.4',38,20,18,'사설','053-421-5222','박성광','010-5135-4777'),
	('00-00','99','018','동인동','동인시티'  			,'달성로21길 44-133','1999.04.29','350.20','345.4',48,30,18,'공설','053-421-5222','장동건','010-5135-4777'),
	('00-00','99','019','동인동','건들바위'  			,'달구벌대로 1975'  ,'2000.04.29','100.80','98.4' ,58,40,18,'사설','053-421-5222','김동현','010-5135-4777'),
	('00-00','99','020','동성동','동성1가'   			,'중앙대로62길 30-3','2001.04.29','214.50','211.4',68,50,18,'공설','053-421-5222','이상혁','010-5135-4777'),
	('00-00','99','021','달성동','달성2가'   			,'명륜로6길 5'      ,'2002.04.29','124.10','100.4',78,60,18,'사설','053-421-5222','우혜미','010-5135-4777'),
	('00-00','99','022','달성동','달성1가'   			,'달성로 2246'      ,'2003.04.29','124.10','100.4',78,60,18,'사설','053-421-5222','우혜미','010-5135-4777'),
	('00-00','99','023','달성동','달성1가'   			,'달성로 2246'      ,'2003.04.29','124.10','100.4',78,60,18,'사설','053-421-5222','우혜미','010-5135-4777'),
	('00-00','99','024','남산동','까치아파트'			,'국채보상로 679-13','1997.04.29','224.80','220.4',28,10,18,'공설','053-421-5222','김혜수','010-5135-4777'),
	('00-00','99','025','남산동','남산그린빌'			,'봉산문화길1-43'   ,'1998.04.29','120.40','120.4',38,20,18,'사설','053-421-5222','박성광','010-5135-4777'),
	('00-00','99','026','동인동','동인시티'  			,'달성로21길 44-133','1999.04.29','350.20','345.4',48,30,18,'공설','053-421-5222','장동건','010-5135-4777'),
	('00-00','99','027','동인동','건들바위'  			,'달구벌대로 1975'  ,'2000.04.29','100.80','98.4' ,58,40,18,'사설','053-421-5222','김동현','010-5135-4777'),
	('00-00','99','028','동성동','동성1가'   			,'중앙대로62길 30-3','2001.04.29','214.50','211.4',68,50,18,'공설','053-421-5222','이상혁','010-5135-4777'),
	('00-00','99','029','달성동','달성2가'   			,'명륜로6길 5'      ,'2002.04.29','124.10','100.4',78,60,18,'사설','053-421-5222','우혜미','010-5135-4777'),
	('00-00','99','030','달성동','달성1가'   			,'달성로 2246'      ,'2003.04.29','124.10','100.4',78,60,18,'사설','053-421-5222','우혜미','010-5135-4777'),
	('00-00','99','031','달성동','달성1가'   			,'달성로 2246'      ,'2003.04.29','124.10','100.4',78,60,18,'사설','053-421-5222','우혜미','010-5135-4777'),
	('00-00','99','032','남산동','까치아파트'			,'국채보상로 679-13','1997.04.29','224.80','220.4',28,10,18,'공설','053-421-5222','김혜수','010-5135-4777'),
	('00-00','99','033','남산동','남산그린빌'			,'봉산문화길1-43'   ,'1998.04.29','120.40','120.4',38,20,18,'사설','053-421-5222','박성광','010-5135-4777'),
	('00-00','99','034','동인동','동인시티'  			,'달성로21길 44-133','1999.04.29','350.20','345.4',48,30,18,'공설','053-421-5222','장동건','010-5135-4777'),
	('00-00','99','035','동인동','건들바위'  			,'달구벌대로 1975'  ,'2000.04.29','100.80','98.4' ,58,40,18,'사설','053-421-5222','김동현','010-5135-4777'),
	('00-00','99','036','동성동','동성1가'   			,'중앙대로62길 30-3','2001.04.29','214.50','211.4',68,50,18,'공설','053-421-5222','이상혁','010-5135-4777'),
	('00-00','99','037','달성동','달성2가'   			,'명륜로6길 5'      ,'2002.04.29','124.10','100.4',78,60,18,'사설','053-421-5222','우혜미','010-5135-4777'),
	('00-00','99','038','달성동','달성1가'   			,'달성로 2246'      ,'2003.04.29','124.10','100.4',78,60,18,'사설','053-421-5222','우혜미','010-5135-4777');

