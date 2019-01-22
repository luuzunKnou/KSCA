/* Manager */
SELECT * FROM manager
	WHERE is_approve=0;
	
UPDATE manager SET is_approve=1
	WHERE id="Tester2";
