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
	('3001','�ǰ���о�'),
	('3002','�ǰ������о�'),
	('3003','���������о�'),
	('3004','�����о�'),
	('3005','���������о�'),
	('3006','��ȸ�����о�'),
	('3007','�����۾���');

-- code name parents_cat 
INSERT INTO cat2(code, name, cat1) VALUES
	('101',' ü�� ��������',			'3001'),
	('102',' ��������',					'3001'),
	('103',' �䰡�����',				'3001'),
	('104',' �ǰ��',					'3001'),
	('101',' �ǰ���������',				'3002'),
	('102',' �ѹ�ġ��',					'3002'),
	('103',' �ȸ�����',					'3002'),
	('104',' �湮��ȣ',					'3002'),
	('101',' ����ȭ����',				'3003'),
	('102',' ����',					'3003'),
	('103',' �νİ�������',				'3003'),
	('104',' ����������',				'3003'),
	('105',' ���λ��',					'3003'),
	('106',' ��Ȱ����������',			'3003'),
	('107',' ���α׷���ǥ��ȸ',			'3003'),
	('101',' ����Ȱ��',					'3004'),
	('102',' �ٵϤ���ⱳ��',			'3004'),
	('103',' ����Ȱ��',					'3004'),
	('104',' �̼�Ȱ��',					'3004'),
	('105',' ��ȭ������Ȱ��',			'3004'),
	('101',' ���μ�����',				'3005'),
	('102',' �Һ������ؿ��汳��',		'3005'),
	('103',' �����ڻ���д뿹�汳��',	'3005'),
	('101',' �湮 �̤��̿뼭��',		'3006'),
	('102',' �ü� �湮 ��������',		'3006'),
	('103',' �ڿ�����Ȱ��',				'3006'),
	('101',' �����۾���',				'3007');

	
-- code city city_code gu gu_code branch branch_code
INSERT INTO area(code, city, city_code, gu, gu_code) VALUES
	('03-01', '�뱸', '03', '�߱�', '01'),
	('00-00', '�׽�Ʈ', '00', '�׽�Ʈ', '00');

-- id password name tel mail is_approve permission area
INSERT INTO manager(id, password, name, tel, mail, is_approve, permission, area) VALUES 
	('mcmoto',md5('mcmoto.ksca'),'�����','010-9004-0726','mcmoto@naver.com',true,'MASTER','03-01'),
	('luuzun',md5('luuzun.ksca'),'�̿���','010-4940-5498','luuzun@naver.com',true,'MASTER','00-00');

INSERT INTO branch(area_code, branch_code, branch) VALUES
	('03-01', '99', '����'),
	('00-00', '99', '����'),
	('00-00', '00', '�׽�Ʈ');

-- area_code branch_code scc_code dong name address reg_date site building member male female own tel president phone manager area      
INSERT INTO scc(area_code, branch_code, scc_code, dong, name, address, reg_date, site, building, member, male, female, own, tel, president, phone) VALUES 
	('00-00','99','001','���굿','�ݿ�������׸��ھ�   ','��ä����� 679-13','1997.04.29','224.80','220.4',28,10,18,'����','053-421-5222','������','010-5135-4777'),
	('00-00','99','002','���굿','����׸���'			,'���깮ȭ��1-43'   ,'1998.04.29','120.40','120.4',38,20,18,'�缳','053-421-5222','�ڼ���','010-5135-4777'),
	('00-00','99','003','���ε�','���ν�Ƽ'  			,'�޼���21�� 44-133','1999.04.29','350.20','345.4',48,30,18,'����','053-421-5222','�嵿��','010-5135-4777'),
	('00-00','99','004','���ε�','�ǵ����'  			,'�ޱ������ 1975'  ,'2000.04.29','100.80','98.4' ,58,40,18,'�缳','053-421-5222','�赿��','010-5135-4777'),
	('00-00','99','005','������','����1��'   			,'�߾Ӵ��62�� 30-3','2001.04.29','214.50','211.4',68,50,18,'����','053-421-5222','�̻���','010-5135-4777'),
	('00-00','99','006','�޼���','�޼�2��'   			,'�����6�� 5'      ,'2002.04.29','124.10','100.4',78,60,18,'�缳','053-421-5222','������','010-5135-4777'),
	('00-00','99','007','�޼���','�޼�1��'  		 	,'�޼��� 2246'      ,'2003.04.29','124.10','100.4',78,60,18,'�缳','053-421-5222','������','010-5135-4777'),
	('00-00','99','008','���굿','�ݿ�������׸��ھ�'	,'��ä����� 679-13','1997.04.29','224.80','220.4',28,10,18,'����','053-421-5222','������','010-5135-4777'),
	('00-00','99','009','���굿','����Ȳ��'	 			,'���깮ȭ��1-43'   ,'1998.04.29','120.40','120.4',38,20,18,'�缳','053-421-5222','�ڼ���','010-5135-4777'),
	('00-00','99','010','���ε�','�ص���ŸŬ����'  	 	,'�޼���21�� 44-133','1999.04.29','350.20','345.4',48,30,18,'����','053-421-5222','�嵿��','010-5135-4777'),
	('00-00','99','011','���ε�','����׸�Ÿ��'  	 	,'�ޱ������ 1975'  ,'2000.04.29','100.80','98.4' ,58,40,18,'�缳','053-421-5222','�赿��','010-5135-4777'),
	('00-00','99','012','������','ȿ���ظ���'			,'�߾Ӵ��62�� 30-3','2001.04.29','214.50','211.4',68,50,18,'����','053-421-5222','�̻���','010-5135-4777'),
	('00-00','99','013','�޼���','����'   	 			,'�����6�� 5'      ,'2002.04.29','124.10','100.4',78,60,18,'�缳','053-421-5222','������','010-5135-4777'),
	('00-00','99','014','�޼���','�޼�1��'   			,'�޼��� 2246'      ,'2003.04.29','124.10','100.4',78,60,18,'�缳','053-421-5222','������','010-5135-4777'),
	('00-00','99','015','�޼���','�޼�1��'   			,'�޼��� 2246'      ,'2003.04.29','124.10','100.4',78,60,18,'�缳','053-421-5222','������','010-5135-4777'),
	('00-00','99','016','���굿','��ġ����Ʈ'			,'��ä����� 679-13','1997.04.29','224.80','220.4',28,10,18,'����','053-421-5222','������','010-5135-4777'),
	('00-00','99','017','���굿','����׸���'			,'���깮ȭ��1-43'   ,'1998.04.29','120.40','120.4',38,20,18,'�缳','053-421-5222','�ڼ���','010-5135-4777'),
	('00-00','99','018','���ε�','���ν�Ƽ'  			,'�޼���21�� 44-133','1999.04.29','350.20','345.4',48,30,18,'����','053-421-5222','�嵿��','010-5135-4777'),
	('00-00','99','019','���ε�','�ǵ����'  			,'�ޱ������ 1975'  ,'2000.04.29','100.80','98.4' ,58,40,18,'�缳','053-421-5222','�赿��','010-5135-4777'),
	('00-00','99','020','������','����1��'   			,'�߾Ӵ��62�� 30-3','2001.04.29','214.50','211.4',68,50,18,'����','053-421-5222','�̻���','010-5135-4777'),
	('00-00','99','021','�޼���','�޼�2��'   			,'�����6�� 5'      ,'2002.04.29','124.10','100.4',78,60,18,'�缳','053-421-5222','������','010-5135-4777'),
	('00-00','99','022','�޼���','�޼�1��'   			,'�޼��� 2246'      ,'2003.04.29','124.10','100.4',78,60,18,'�缳','053-421-5222','������','010-5135-4777'),
	('00-00','99','023','�޼���','�޼�1��'   			,'�޼��� 2246'      ,'2003.04.29','124.10','100.4',78,60,18,'�缳','053-421-5222','������','010-5135-4777'),
	('00-00','99','024','���굿','��ġ����Ʈ'			,'��ä����� 679-13','1997.04.29','224.80','220.4',28,10,18,'����','053-421-5222','������','010-5135-4777'),
	('00-00','99','025','���굿','����׸���'			,'���깮ȭ��1-43'   ,'1998.04.29','120.40','120.4',38,20,18,'�缳','053-421-5222','�ڼ���','010-5135-4777'),
	('00-00','99','026','���ε�','���ν�Ƽ'  			,'�޼���21�� 44-133','1999.04.29','350.20','345.4',48,30,18,'����','053-421-5222','�嵿��','010-5135-4777'),
	('00-00','99','027','���ε�','�ǵ����'  			,'�ޱ������ 1975'  ,'2000.04.29','100.80','98.4' ,58,40,18,'�缳','053-421-5222','�赿��','010-5135-4777'),
	('00-00','99','028','������','����1��'   			,'�߾Ӵ��62�� 30-3','2001.04.29','214.50','211.4',68,50,18,'����','053-421-5222','�̻���','010-5135-4777'),
	('00-00','99','029','�޼���','�޼�2��'   			,'�����6�� 5'      ,'2002.04.29','124.10','100.4',78,60,18,'�缳','053-421-5222','������','010-5135-4777'),
	('00-00','99','030','�޼���','�޼�1��'   			,'�޼��� 2246'      ,'2003.04.29','124.10','100.4',78,60,18,'�缳','053-421-5222','������','010-5135-4777'),
	('00-00','99','031','�޼���','�޼�1��'   			,'�޼��� 2246'      ,'2003.04.29','124.10','100.4',78,60,18,'�缳','053-421-5222','������','010-5135-4777'),
	('00-00','99','032','���굿','��ġ����Ʈ'			,'��ä����� 679-13','1997.04.29','224.80','220.4',28,10,18,'����','053-421-5222','������','010-5135-4777'),
	('00-00','99','033','���굿','����׸���'			,'���깮ȭ��1-43'   ,'1998.04.29','120.40','120.4',38,20,18,'�缳','053-421-5222','�ڼ���','010-5135-4777'),
	('00-00','99','034','���ε�','���ν�Ƽ'  			,'�޼���21�� 44-133','1999.04.29','350.20','345.4',48,30,18,'����','053-421-5222','�嵿��','010-5135-4777'),
	('00-00','99','035','���ε�','�ǵ����'  			,'�ޱ������ 1975'  ,'2000.04.29','100.80','98.4' ,58,40,18,'�缳','053-421-5222','�赿��','010-5135-4777'),
	('00-00','99','036','������','����1��'   			,'�߾Ӵ��62�� 30-3','2001.04.29','214.50','211.4',68,50,18,'����','053-421-5222','�̻���','010-5135-4777'),
	('00-00','99','037','�޼���','�޼�2��'   			,'�����6�� 5'      ,'2002.04.29','124.10','100.4',78,60,18,'�缳','053-421-5222','������','010-5135-4777'),
	('00-00','99','038','�޼���','�޼�1��'   			,'�޼��� 2246'      ,'2003.04.29','124.10','100.4',78,60,18,'�缳','053-421-5222','������','010-5135-4777');

