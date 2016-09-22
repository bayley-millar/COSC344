
CREATE OR REPLACE TRIGGER total_appointments
AFTER INSERT OR UPDATE OR DELETE OF car_id ON appointment
FOR EACH ROW
BEGIN
  IF INSERTING THEN
    UPDATE car
      SET total_appointment = total_appointment + 1
        WHERE car_id = :NEW.car_id;

  ELSE -- deleting
    UPDATE car
      SET total_appointment = total_appointment - 1
        WHERE car_id = :OLD.car_id;
  END IF;
END;
/
