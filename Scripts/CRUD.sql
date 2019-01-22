show tables;

-- agency
INSERT INTO agency(name, manager, tel) VALUES 
	("중구체육회","홍길동","010-1588-3982");

SELECT code, name, manager, tel FROM agency;

UPDATE agency SET 
		name	="TestAgency",
		manager	="",
		tel		=""
		WHERE code = 4;

DELETE FROM agency WHERE code=4;



-- area
INSERT INTO area(code, manager, city, city_code, gu, gu_code, branch, branch_code) VALUES
	("99-99-99", "mcmoto", "대구", "03", "중구", "01", "없음", "99");

SELECT code, manager, city, city_code, gu, gu_code, branch, branch_code FROM area;

UPDATE area	SET 
		code		="99-99-99",
		manager		="luuzun",
		city		="Upcity",
		city_code	="99",
		gu			="UpGu", 
		gu_code		="99", 
		branch		="UpBr",
		branch_code	="99" 
		WHERE code="99-99-99";
		
DELETE FROM  area WHERE code = "99-99-99";



-- cat1
INSERT INTO cat1(code, name) VALUES
	("9999","건강운동분야");

SELECT code, name FROM cat1;

UPDATE cat1 SET
		code	="9999",
		name	="Test"
		WHERE code="9999"; 

DELETE FROM cat1 WHERE code = "9999"; 



-- cat2
INSERT INTO cat2(code, name, parents_cat) VALUES
	("9999999"," 체조 ㆍ댄스교실","3001");

SELECT code, name, parents_cat FROM cat2;

UPDATE cat2 SET
		code		="9999999",
		name		="Test",
		parents_cat	="3004"
		WHERE code="9999999"; 

DELETE FROM cat2 WHERE code = "9999999";



-- manager
INSERT INTO manager(id, password, name, tel, mail, is_approve, permission) VALUES
	("Tester", "1234", "이원준", "010-4940-5498", "luuzun@naver.com", FALSE,0);

SELECT id, password, name, tel, mail, is_approve, permission FROM manager;

UPDATE manager SET
	 	password	="4321", 
		name		="Tester", 
		tel			="999-9999-9999", 
		mail		="Tester@test.com", 
		is_approve	=TRUE, 
		permission	=0
		WHERE id = "Tester"

DELETE FROM manager WHERE id = "Tester";



-- program
INSERT INTO program(name, cat, agency) VALUES 	
	("Test Program", "3001101",1);

SELECT code, name, cat, agency FROM program;

UPDATE program SET
	 	name 	="Test",
		cat		="3001102",
		agency	=1
		WHERE code = 4;

DELETE FROM program WHERE code = 4;



-- scc
INSERT INTO scc(code, name, area, manager) VALUES 
	("99-99-99-999","까치아파트","03-01-99","mcmoto" );

SELECT code, name, area, manager FROM scc;

UPDATE scc SET 
	code 	="99-99-99-999",
	name 	="UpTest", 
	area	="03-01-99", 
	manager	="luuzun"
	WHERE code="99-99-99-999";

DELETE FROM scc WHERE code = "99-99-99-999";



-- schedule
INSERT INTO schedule(manager, ssc, program, begin_date, end_date, time, monthly_oper, active_user) VALUES 
	("luuzun", "03-01-99-001", 1, "2019-01-01", "2019-01-31", "02:00:00", 8, 31);

SELECT code, manager, ssc, program, begin_date, end_date, time, monthly_oper, active_user FROM schedule;

UPDATE schedule SET 
	manager			="mcmoto", 
	ssc				="03-01-99-002", 
	program			="2", 
	begin_date		="2019-02-02", 
	end_date		="2019-02-28", 
	time			="03:00:00", 
	monthly_oper	=100,
	active_user 	=100
	WHERE code = 17;

DELETE FROM schedule WHERE code = 17;


