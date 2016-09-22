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
