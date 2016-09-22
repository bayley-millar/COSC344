CREATE OR REPLACE TRIGGER part_total_cost
AFTER INSERT OR UPDATE OR DELETE OF quantity ON part
BEGIN
IF INSERTING THEN
UPDATE part
SET total_cost = quantity + :NEW.quantity * cost
WHERE part_id= :NEW.p_id;
ELSEIF UPDATING THEN
UPDATE part
SET total_cost = total_cost + :NEW.quantity - :OLD.quantity * cost
WHERE part_id= :OLD.p_id;
ELSE -- deleting
UPDATE part
SET total_cost = total_cost - :OLD.quantity * cost
WHERE part_id = :OLD.p_id;
END IF;
END;
