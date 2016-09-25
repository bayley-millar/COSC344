--Trigger for changing the derived attribute balance based on the amount value
--in the transactions table.
--On insert of a new amount in transactions the balance of the account is
--set.
--For deletion of one row in a transaction, the deletion will cause
--the account balace to be changed.
--If an updated is made in any of the rows in transaction, the account
--balance is updated.

CREATE OR REPLACE TRIGGER accountbalance
AFTER INSERT OR UPDATE OR DELETE OF amount ON transactions
FOR EACH ROW
BEGIN
  IF INSERTING THEN
    UPDATE account
      SET balance = balance - :NEW.amount
        WHERE acc_id = :NEW.account_ID;

  ELSIF UPDATING THEN
    UPDATE account
      SET balance = balance - :NEW.amount + :OLD.amount
        WHERE acc_id = :OLD.account_ID;

  ELSE -- deleting
    UPDATE account
      SET balance  = balance + :OLD.amount
        WHERE acc_id = :OLD.account_ID;
  END IF;
END;
/

--Trigger for changing the derived attribute total_appointments based on the
--car_id in the appointment table.
--On insert of a new row, the total appointments is incremented by one.
--This gives us the total number of appointments for that given car.
--Updating the car_id for an appointment will increment the total_appointments
--for the new car that is taking the appointment and decrement the
--total_appointments for the old car being replaced.
--Deletion of a row will result in the total appointments decrementing by one.

CREATE OR REPLACE TRIGGER total_appointments
AFTER INSERT OR UPDATE OR DELETE OF car_id ON appointment
FOR EACH ROW
BEGIN
  IF INSERTING THEN
    UPDATE car
      SET total_appointment = total_appointment + 1
        WHERE car_id = :NEW.car_id;

  ELSIF UPDATING THEN
    UPDATE car
      SET total_appointment = total_appointment + 1
        WHERE car_id = :NEW.car_id;
    UPDATE car
      SET total_appointment = total_appointment - 1
        WHERE car_id = :OLD.car_id;

  ELSE --deleting
    UPDATE car
      SET total_appointment = total_appointment - 1
        WHERE car_id = :OLD.car_id;
  END IF;
END;
/
