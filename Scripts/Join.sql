-- readManagerHasArea
SELECT DISTINCT id, password, name, tel, mail, is_approve, permission, 
		city, city_code, gu, gu_code
	FROM manager, area
	WHERE id="mcmoto";
	
SELECT * FROM cat1, cat2 WHERE cat1.code=cat2.cat1;

SELECT cat1.code AS cat1_code, cat1.name AS cat1_name, 
	   cat2.code AS cat2_code, cat2.name AS cat2_name 
	FROM cat1, cat2 
	   	WHERE cat1.code=cat2.cat1;
	   	
	   
SELECT 
	c1. code as code1,
	c1. name as name1,
	c2. code as code2,
	c2. name as name2,
	c2. cat1 AS cat1
  	FROM cat1 c1 LEFT join cat2 c2  
  		ON c1.code = c2.cat1;
  		
  	
SELECT program.code AS pcode, program.name AS pname, 
		program.cat1 AS c1code, cat1.name AS c1name, 
		program.cat2 AS c2code, cat2.name AS c2name, 
		program.agency AS acode, agency.name AS aname, agency.manager AS manager, agency.tel AS tel
	FROM program
		JOIN cat1 ON program.cat1=cat1.code
		JOIN cat2 ON program.cat2=cat2.code AND program.cat1=cat2.cat1
		JOIN agency ON program.agency=agency.code
	WHERE program.area='03-01';

SELECT  program.code 	AS pcode, 
		program.name 	AS pname, 
		program.cat1 	AS c1code, 
		cat1.name 		AS c1name, 
		program.cat2 	AS c2code, 
		cat2.name 		AS c2name, 
		program.agency 	AS acode, 
		agency.name 	AS aname, 
		ifnull(agency.manager,'') AS manager, 
		ifnull(agency.tel,'') 	  AS tel
	FROM program
		JOIN cat1 ON program.cat1=cat1.code
		JOIN cat2 ON program.cat2=cat2.code AND program.cat1=cat2.cat1
		JOIN agency ON program.agency=agency.code
	WHERE program.code='1';

SELECT offer.code				AS code,
	   offer.area_code			AS area_code,
	   offer.branch_code		AS branch_code,
	   offerprogram.reg_month	AS reg_month,
	   offer.scc_code			AS scc_code,
	   offer.program			AS program,
	   offerprogram.begin_date	AS begin_date,
	   offerprogram.end_date	AS end_date,	   
	   offer.monthly_oper		AS monthly_oper,
	   offer.active_user		AS active_user,
	   offerprogram.color		AS color,
	   scc.name					AS scc_name,
	   schedule.code			AS sc_code,
	   schedule.date			AS sc_date,
	   program.name				AS program_name
	FROM offer 
		JOIN scc ON scc.area_code=offer.area_code 
					AND scc.branch_code=offer.branch_code
					AND scc.scc_code=offer.scc_code
		JOIN schedule 	  ON schedule.offer=offer.code
		JOIN offerprogram ON offer.program=offerprogram.code
		JOIN program 	  ON offerprogram.program=program.code
	WHERE offer.area_code='03-01'
			AND MONTH(schedule.date)='02'
			AND YEAR(schedule.date)='2019';
		-- AND schedule.date BETWEEN date('2019-02-01') AND date('2019-02-28');

	
	
SELECT  offerprogram.code		AS code,
		program.name			AS name,
		offerprogram.reg_month	AS reg_month,
		offerprogram.begin_date	AS begin_date,
		offerprogram.end_date	AS end_date,
		offerprogram.color		AS color,
		program.code			AS program
	FROM offerprogram
		JOIN program ON offerprogram.program=program.code
	WHERE program.area='03-01'
		AND offerprogram.reg_month='2019-02-01';
		
SELECT DISTINCT YEAR(date), MONTH(date) 
	FROM schedule
		JOIN offer ON schedule.offer=offer.code
		WHERE offer.area_code='03-01';

	
SELECT  *, 
		DAYOFWEEK(date), -- 날짜의 요일(1: 일요일 7:토요일)
		-- DAYOFWEEK(CONCAT_WS('-',YEAR(date),MONTH(date),'01')) AS firstDay, -- 첫 날의 요일
		CEIL((DAY(date) + DAYOFWEEK(CONCAT_WS('-',YEAR(date),MONTH(date),'01')))/7) AS weekCnt-- 몇 번째 주?
		FROM schedule
	WHERE MONTH(date)='02' AND YEAR(date)='2019';
	

SELECT  scc.area_code			AS area_code,
		scc.branch_code			AS branch_code,
		scc.scc_code			AS scc_code,
		scc.name				AS scc_name,
		cat1.code				AS cat1_code,
		cat1.name				AS cat1_name,
		cat2.code				AS cat2_code,
		cat2.name				AS cat2_name,
		program.name			AS program_name,
		offerprogram.begin_date	AS begin_date,
		offerprogram.end_date	AS end_date,
		offer.active_user		AS active_user,
		agency.name				AS agency_name,
		offer.monthly_oper		AS monthly_oper
	FROM scc 
		JOIN offer ON scc.area_code=offer.area_code 
					AND scc.branch_code	= offer.branch_code
					AND scc.scc_code	= offer.scc_code
		JOIN offerprogram ON offer.program = offerprogram.code
		JOIN program 	  ON offerprogram.program = program.code
		JOIN cat2		  ON program.cat2 = cat2.code 
		JOIN cat1		  ON program.cat1 = cat1.code
		JOIN agency		  ON program.agency = agency.code
	WHERE scc.area_code='03-01'
		AND offerprogram.reg_month='2019-02-01';