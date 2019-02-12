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

SELECT ifnull(tel,1) FROM agency;