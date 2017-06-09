-- TIGER FOR ADDING RESERVATIONS TO NOTIFICATIONS

CREATE OR REPLACE FUNCTION reservation_notification()
  RETURNS TRIGGER AS
$$
BEGIN
  CASE WHEN new.date_from >= current_date THEN
    INSERT INTO notification ( id_type, id_department, priority, received_time, done) VALUES ( 4, 4, 1, current_date, 'true' );
    ELSE
    INSERT INTO notification ( id_type, id_department, priority, received_time, done) VALUES ( 4, 4, 1, new.date_from - INTERVAL '5 days', 'true' );
  END CASE;
END;
$$
LANGUAGE PLPGSQL;

CREATE TRIGGER reservation_notification AFTER INSERT ON reservation
FOR EACH ROW EXECUTE PROCEDURE reservation_notification();

----