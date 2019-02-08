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