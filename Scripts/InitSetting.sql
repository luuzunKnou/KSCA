DROP DATABASE ksca;
CREATE DATABASE ksca;
USE ksca;

CREATE TABLE cat1 (
	code VARCHAR(10) NOT NULL,
	name VARCHAR(50) NOT NULL,
	PRIMARY KEY (code)
);

CREATE TABLE cat2 (
	code        VARCHAR(10) NOT NULL,
	name        VARCHAR(50) NOT NULL,
	parents_cat VARCHAR(10) NOT NULL,
	PRIMARY KEY (code),
	FOREIGN KEY (parents_cat)
		REFERENCES cat1 (code) ON DELETE CASCADE
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
		REFERENCES cat2 (code) ON DELETE CASCADE
);

CREATE TABLE manager (
	id         VARCHAR(50) NOT NULL, 
	password   VARCHAR(20) NOT NULL, 
	name       VARCHAR(12) NOT NULL, 
	tel        VARCHAR(30) NOT NULL, 
	mail       VARCHAR(40) NOT NULL, 
	is_approve BOOLEAN     DEFAULT 0, 
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
	code    VARCHAR(15) NOT NULL,
	name    VARCHAR(50) NOT NULL,
	area    VARCHAR(15) NOT NULL,
	manager VARCHAR(50) NOT NULL,
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

-- -------------------------------------------------
-- code name
INSERT INTO cat1(code, name) VALUES
	('3001','건강운동분야'),
	('3002','건강관리분야'),
	('3003','교육ㆍ상담분야'),
	('3004','여가분야'),
	('3005','권익증진분야'),
	('3006','사회참여분야'),
	('3007','공동작업장');

-- code name parents_cat 
INSERT INTO cat2(code, name, parents_cat) VALUES
	('3001101',' 체조 ㆍ댄스교실',			'3001'),
	('3001102',' 웃음교실',					'3001'),
	('3001103',' 요가ㆍ명상',				'3001'),
	('3001104',' 건강운동',					'3001'),
	('3002101',' 건강검진관련',				'3002'),
	('3002102',' 한방치료',					'3002'),
	('3002103',' 안마교실',					'3002'),
	('3002104',' 방문간호',					'3002'),
	('3003101',' 정보화교육',				'3003'),
	('3003102',' 언어교육',					'3003'),
	('3003103',' 인식개선교육',				'3003'),
	('3003104',' 에너지교육',				'3003'),
	('3003105',' 노인상담',					'3003'),
	('3003106',' 생활ㆍ안전교육',				'3003'),
	('3003107',' 프로그램발표대회',			'3003'),
	('3004101',' 음악활동',					'3004'),
	('3004102',' 바둑ㆍ장기교실',				'3004'),
	('3004103',' 문학활동',					'3004'),
	('3004104',' 미술활동',					'3004'),
	('3004105',' 문화ㆍ공연활동',				'3004'),
	('3005101',' 노인성교육',				'3005'),
	('3005102',' 소비자피해예방교육',			'3005'),
	('3005103',' 노인자살ㆍ학대예방교육',		'3005'),
	('3006101',' 방문 이ㆍ미용서비스',			'3006'),
	('3006102',' 시설 방문 위문공연',			'3006'),
	('3006103',' 자원봉사활동',				'3006'),
	('3007101',' 공동작업장',				'3007');

-- code name manager tel
INSERT INTO agency(name) VALUES 
	('중구체육회'),
	('중구보건소'),
	('건강보험공단');

-- code name cat agency
INSERT INTO program(name, cat, agency) VALUES 	
	('스트레칭 건강체조'	, '3001101',1), -- 1
	('기초검진체력측정'	, '3002101',2), -- 2
	('백세건강운동교실'	, '3001101',3); -- 3

-- id password name tel mail is_approve permission
INSERT INTO manager(id, password, name, tel, mail, is_approve, permission) VALUES
	('luuzun','1234','이원준','010-4940-5498','luuzun@naver.com',true,'MASTER'),
	('mcmoto','1234','우금주','010-9004-0726','mcmoto@naver.com',true,'MANAGER');
	
-- code manager city city_code gu gu_code branch branch_code
INSERT INTO area(code, manager, city, city_code, gu, gu_code, branch, branch_code) VALUES
	('03-01-99', 'mcmoto', '대구', '03', '중구', '01', '없음' , '99'),
	('03-01-01', 'mcmoto', '대구', '03', '중구', '01', '남산동', '01'),
	('03-01-02', 'mcmoto', '대구', '03', '중구', '01', '대봉동', '02'),
	('03-01-03', 'mcmoto', '대구', '03', '중구', '01', '봉덕동', '03'),
	('03-01-04', 'mcmoto', '대구', '03', '중구', '01', '비산동', '04');

-- code name area manager
INSERT INTO scc(code, name, area, manager) VALUES 
	('03-01-99-001','까치아파트'	,'03-01-99','mcmoto' ),
	('03-01-99-002','센트로팰리스'	,'03-01-99','mcmoto' ),
	('03-01-99-003','봉산뜨란채'	,'03-01-99','mcmoto' ),
	('03-01-99-004','삼덕청아람'	,'03-01-99','mcmoto' ),
	('03-01-99-005','보성황실'		,'03-01-99','mcmoto' ),
	('03-01-99-006','동인시티'		,'03-01-99','mcmoto' ),
	('03-01-99-007','보성송림'		,'03-01-99','mcmoto' ),
	('03-01-99-008','남산복지관'	,'03-01-99','mcmoto' );
	
-- code manager ssc program begin_date end_date time monthly_oper active_user 
INSERT INTO schedule(manager, ssc, program, begin_date, end_date, time, monthly_oper, active_user) VALUES 
	('mcmoto','03-01-99-001', 1, '2019-01-01', '2019-01-31', '02:00:00', 8, 31),
	('mcmoto','03-01-99-002', 1, '2019-01-01', '2019-01-31', '02:00:00', 5, 52),
	('mcmoto','03-01-99-003', 1, '2019-01-01', '2019-01-31', '02:00:00', 8, 12),
	('mcmoto','03-01-99-004', 1, '2019-01-01', '2019-01-31', '02:00:00', 4, 42),
	('mcmoto','03-01-99-005', 1, '2019-01-01', '2019-01-31', '02:00:00', 7, 73),
	('mcmoto','03-01-99-006', 1, '2019-01-01', '2019-01-31', '02:00:00', 9, 16),
	('mcmoto','03-01-99-007', 1, '2019-01-01', '2019-01-31', '02:00:00', 8, 15),
	('mcmoto','03-01-99-007', 1, '2019-01-01', '2019-01-31', '02:00:00', 1, 71),
	('mcmoto','03-01-99-001', 2, '2019-01-01', '2019-01-31', '02:00:00', 8, 31),
	('mcmoto','03-01-99-002', 2, '2019-01-01', '2019-01-31', '02:00:00', 5, 52),
	('mcmoto','03-01-99-003', 2, '2019-01-01', '2019-01-31', '02:00:00', 8, 12),
	('mcmoto','03-01-99-004', 2, '2019-01-01', '2019-01-31', '02:00:00', 4, 42),
	('mcmoto','03-01-99-005', 2, '2019-01-01', '2019-01-31', '02:00:00', 7, 73),
	('mcmoto','03-01-99-006', 2, '2019-01-01', '2019-01-31', '02:00:00', 9, 16),
	('mcmoto','03-01-99-007', 2, '2019-01-01', '2019-01-31', '02:00:00', 8, 15),
	('mcmoto','03-01-99-007', 2, '2019-01-01', '2019-01-31', '02:00:00', 1, 71);
