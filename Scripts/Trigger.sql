DELIMITER $$
CREATE TRIGGER updateArea
AFTER UPDATE ON area
FOR EACH ROW
BEGIN
	UPDATE area SET code 
END $$
DELIMITER ;