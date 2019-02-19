/* Manager */
SELECT * FROM manager
	WHERE is_approve=0;
	
UPDATE manager SET is_approve=1
	WHERE id="Tester2";


INSERT INTO scc(area_code, branch_code, scc_code, dong, name, address, reg_date, site, building, member, male, female, own, tel, president, phone) VALUES 
	('03-01','11','003','남산동','까치아파트','국채보상로 679-13','Fri Nov 11 00:00:00 KST 2011','224.80','220.4',28,10,18,'공설','053-421-5222','김혜수','010-5135-4777');


SELECT color FROM offer 
	WHERE program='3'
	GROUP BY color 
	ORDER BY 
		count(*) DESC LIMIT 1;
	