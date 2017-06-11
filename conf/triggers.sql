-- TRIGGER FOR ADDING RESERVATIONS TO NOTIFICATIONS

CREATE OR REPLACE FUNCTION reservation_notification()
  RETURNS TRIGGER AS
$$
BEGIN
  INSERT INTO notification ( id_type, id_department, priority, received_time, done) VALUES ( 4, 4, 1, current_time, 'true' );
		new.id_notification = currval('notification_id_seq')::INTEGER;
		INSERT INTO "order" (id_guest, received_date, id_order_category, id_notification) VALUES (new.id_guest, current_date, 1, new.id_notification);
	return new;
END;
$$
LANGUAGE PLPGSQL;

CREATE TRIGGER reservation_notification AFTER INSERT ON reservation
FOR EACH ROW EXECUTE PROCEDURE reservation_notification();

----

CREATE OR REPLACE FUNCTION booker_check() RETURNS trigger AS $booker_check$
BEGIN
  IF NEW.booker IS NULL then
    NEW.booker = NEW.id;
  END IF;
  RETURN NEW;
END;
$booker_check$ LANGUAGE plpgsql;

CREATE TRIGGER booker_check BEFORE INSERT ON guest
FOR EACH ROW EXECUTE PROCEDURE booker_check();
