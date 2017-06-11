CREATE OR REPLACE FUNCTION rooms_stats(after timestamp with time zone, before timestamp with time zone)
  RETURNS TABLE(count bigint, room_id integer) AS
  $$
    BEGIN
      RETURN QUERY SELECT COUNT(*), room.id FROM
          reservation INNER JOIN room ON reservation.id_room = room.id
        WHERE date_from <= before AND after <= date_to
        GROUP BY room.id ORDER BY count DESC;
    END
  $$ language 'plpgsql';

CREATE OR REPLACE FUNCTION reservation_people_count(reservation_id bigint) RETURNS bigint AS
  $$
    DECLARE
      ret bigint;
    BEGIN
      SELECT COUNT(*) INTO ret FROM guest WHERE booker=(
        SELECT id_guest FROM reservation INNER JOIN guest ON reservation.id_guest = guest.id WHERE reservation.id = reservation_id
      );
      RETURN ret;
    END
  $$ language 'plpgsql';

CREATE OR REPLACE FUNCTION reservation_stats(after timestamp with time zone, before timestamp with time zone)
  RETURNS TABLE(day date, price numeric(8, 2)) AS
  $$
    BEGIN
      RETURN QUERY SELECT range::date, coalesce(sum(room.price * reservation_people_count(reservation.id)), 0) price FROM
        reservation INNER JOIN room ON reservation.id_room = room.id
        RIGHT JOIN generate_series(after, before, '1 day'::interval) range ON range::date=reservation.date_from
      GROUP BY range::date;
    END
  $$ language 'plpgsql';
