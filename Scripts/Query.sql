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

SELECT count(*) From schedule
	WHERE offer=1;

 SELECT count(*) From schedule WHERE offer='1' AND date='2019-02-01 00:00:00'; 
 
INSERT INTO schedule(offer, date) VALUES 
	(1, '2019-02-01'),
	(1, '2019-02-08');
	
SELECT * FROM offerprogram;
SELECT * FROM offer;
SELECT * FROM schedule;

use ksca;

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
		WHERE scc.area_code='00-00'
			AND offer.monthly_oper != 0 
			AND offerprogram.reg_month='2019-04-01';
			
		select * from manager;
		update manager set is_approve=1 where id='luuzun';