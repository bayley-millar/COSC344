--1: a SELECT using a LIKE something
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
--Finding genders of customers from account

SELECT *
       FROM account
       WHERE ca_id IN
             (SELECT c_id
                     FROM customer
                     WHERE c_gender = 'F');

--5: a query using a correlated subquery
-- finds all staff that have a higher salary than the average salary

SELECT c_id, c_fname
FROM customer s
WHERE 1 <
(SELECT COUNT(*)
FROM car
WHERE c_id = s.c_id);



--6: a query involving COUNT(*)
-- counts how many accounts there are

SELECT COUNT(*)
       FROM account;


--7: a selective DELETE
--

DELETE  FROM staff WHERE date_of_birth < TO_DATE('01-01-1985','DD-MM-YYYY'); 



--8 a SELECT that retrieves data from 3 tables
-- Select customer name, phonenumber, car name, appointment time

SELECT c.c_fname, c.c_ph_no, s.car_id, a.time_booked_for
FROM customer c, car s, appointment a
WHERE c.c_fname = 'Bilbo';
 

 
