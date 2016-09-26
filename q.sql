


select model, count(model) from car group by model order by count(model) DESC;



select * from parts where model = 'Cooper';



--Selects all the customers that have an account balance above the AVG.
select * from account b where balance > (select avg(balance) from account);



-- Select the max account balance from a certain state

select * from account where balance = (select max(balance) from account where last_visit >= TO_DATE('25-11-1980','DD-MM-YYYY'));

-- select customers with more than one car

select c_id, c_fname, c_lname from customer c where 1 < (SELECT count(*) from car where c_id = c.c_id);

