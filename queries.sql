--1: a SELECT using a LIKE
--Finding a staff members address starting with "I"
SELECT * 
FROM staff
WHERE s_address LIKE 'I%';


--2: a SELECT on a DATE column
--Finding a staff members DOB
SELECT * 
FROM staff
WHERE date_of_birth = TO_DATE('12-12-1996','DD-MM-YYYY');

--3: a query using GROUP BY and HAVING
--Finding staff memebers with a salary larger than 100,000
SELECT s_fname, MAX(salary) 
FROM staff
GROUP BY s_fname
HAVING MAX(salary) > 100000;

--4: a query using a subquery
--
SELECT 
 
