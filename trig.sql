--Trigger for changing the derrived attribute total appointments based on the
--car_id in the appointment table.
--On insert of a new row, the total appointments for a car_id is updated by one.
--This gives us the a total number of appointments in the database.
--For update of a car_id or deletion of a row the total appointments value for
--the old car_id will be decremented by one.

CREATE OR REPLACE TRIGGER total_appointments
AFTER INSERT OR UPDATE OR DELETE OF car_id ON appointment
FOR EACH ROW
BEGIN
  IF INSERTING THEN
    UPDATE car
      SET total_appointment = total_appointment + 1
        WHERE car_id = :NEW.car_id;

  ELSE -- updating or deleting
    UPDATE car
      SET total_appointment = total_appointment - 1
        WHERE car_id = :OLD.car_id;
  END IF;
END;
/

--Trigger for changing the derived attribute balance from the account table
--On insert of a new amount in transactions the balance of account is updated.
--For deletion of one row in a transaction, the deletion will cause
--the account balace to be changed.
--If an update is made in any of the rows in transaction, the account
--balance is updated.

CREATE OR REPLACE TRIGGER accountbalance
AFTER INSERT OR UPDATE OR DELETE OF amount ON transactions
FOR EACH ROW
BEGIN
  IF INSERTING THEN
    UPDATE account
      SET balance = balance + :NEW.amount
        WHERE acc_id = :NEW.account_ID;

  ELSIF UPDATING THEN
    UPDATE account
      SET balance = balance + :NEW.amount - :OLD.amount
        WHERE acc_id = :OLD.account_ID;

  ELSE -- deleting
    UPDATE account
      SET balance  = balance - :OLD.amount
        WHERE acc_id = :OLD.account_ID;
  END IF;
END;
/
