
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


/*UPDATE offer 
	SET monthly_oper = 0
	WHERE code = 7;

SELECT * FROM schedule;
SELECT code, monthly_oper FROM offer;

INSERT INTO schedule(offer, date) VALUES 
	(4, '2111-11-11'),
	(4, '2111-11-11'),
	(4, '2111-11-11');
	
DELETE FROM schedule WHERE offer=7;*/