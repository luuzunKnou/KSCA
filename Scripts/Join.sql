-- readManagerHasArea
SELECT DISTINCT id, password, name, tel, mail, is_approve, permission, 
		city, city_code, gu, gu_code
	FROM manager, area
	WHERE id="mcmoto";