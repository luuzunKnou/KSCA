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
	('3001','�ǰ���о�'),
	('3002','�ǰ������о�'),
	('3003','���������о�'),
	('3004','�����о�'),
	('3005','���������о�'),
	('3006','��ȸ�����о�'),
	('3007','�����۾���');

-- code name parents_cat 
INSERT INTO cat2(code, name, parents_cat) VALUES
	('3001101',' ü�� ��������',			'3001'),
	('3001102',' ��������',					'3001'),
	('3001103',' �䰡�����',				'3001'),
	('3001104',' �ǰ��',					'3001'),
	('3002101',' �ǰ���������',				'3002'),
	('3002102',' �ѹ�ġ��',					'3002'),
	('3002103',' �ȸ�����',					'3002'),
	('3002104',' �湮��ȣ',					'3002'),
	('3003101',' ����ȭ����',				'3003'),
	('3003102',' ����',					'3003'),
	('3003103',' �νİ�������',				'3003'),
	('3003104',' ����������',				'3003'),
	('3003105',' ���λ��',					'3003'),
	('3003106',' ��Ȱ����������',				'3003'),
	('3003107',' ���α׷���ǥ��ȸ',			'3003'),
	('3004101',' ����Ȱ��',					'3004'),
	('3004102',' �ٵϤ���ⱳ��',				'3004'),
	('3004103',' ����Ȱ��',					'3004'),
	('3004104',' �̼�Ȱ��',					'3004'),
	('3004105',' ��ȭ������Ȱ��',				'3004'),
	('3005101',' ���μ�����',				'3005'),
	('3005102',' �Һ������ؿ��汳��',			'3005'),
	('3005103',' �����ڻ���д뿹�汳��',		'3005'),
	('3006101',' �湮 �̤��̿뼭��',			'3006'),
	('3006102',' �ü� �湮 ��������',			'3006'),
	('3006103',' �ڿ�����Ȱ��',				'3006'),
	('3007101',' �����۾���',				'3007');

-- code name manager tel
INSERT INTO agency(name) VALUES 
	('�߱�ü��ȸ'),
	('�߱����Ǽ�'),
	('�ǰ��������');

-- code name cat agency
INSERT INTO program(name, cat, agency) VALUES 	
	('��Ʈ��Ī �ǰ�ü��'	, '3001101',1), -- 1
	('���ʰ���ü������'	, '3002101',2), -- 2
	('�鼼�ǰ������'	, '3001101',3); -- 3

-- id password name tel mail is_approve permission
INSERT INTO manager(id, password, name, tel, mail, is_approve, permission) VALUES
	('luuzun','1234','�̿���','010-4940-5498','luuzun@naver.com',true,'MASTER'),
	('mcmoto','1234','�����','010-9004-0726','mcmoto@naver.com',true,'MANAGER');
	
-- code manager city city_code gu gu_code branch branch_code
INSERT INTO area(code, manager, city, city_code, gu, gu_code, branch, branch_code) VALUES
	('03-01-99', 'mcmoto', '�뱸', '03', '�߱�', '01', '����' , '99'),
	('03-01-01', 'mcmoto', '�뱸', '03', '�߱�', '01', '���굿', '01'),
	('03-01-02', 'mcmoto', '�뱸', '03', '�߱�', '01', '�����', '02'),
	('03-01-03', 'mcmoto', '�뱸', '03', '�߱�', '01', '������', '03'),
	('03-01-04', 'mcmoto', '�뱸', '03', '�߱�', '01', '��굿', '04');

-- code name area manager
INSERT INTO scc(code, name, area, manager) VALUES 
	('03-01-99-001','��ġ����Ʈ'	,'03-01-99','mcmoto' ),
	('03-01-99-002','��Ʈ���Ӹ���'	,'03-01-99','mcmoto' ),
	('03-01-99-003','����߶�ä'	,'03-01-99','mcmoto' ),
	('03-01-99-004','���û�ƶ�'	,'03-01-99','mcmoto' ),
	('03-01-99-005','����Ȳ��'		,'03-01-99','mcmoto' ),
	('03-01-99-006','���ν�Ƽ'		,'03-01-99','mcmoto' ),
	('03-01-99-007','�����۸�'		,'03-01-99','mcmoto' ),
	('03-01-99-008','���꺹����'	,'03-01-99','mcmoto' );
	
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
