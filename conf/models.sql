CREATE TABLE classes (
	id                   serial  NOT NULL,
	name                 varchar(100)  NOT NULL,
	base_price           numeric(8,2)  ,
	CONSTRAINT pk_classes PRIMARY KEY ( id )
 );

COMMENT ON TABLE classes IS 'Different types of room standard';

COMMENT ON COLUMN classes.base_price IS 'Base price is only an information for setting price for a particular room.';

CREATE TABLE notification_types ( 
	id                   serial  NOT NULL,
	name                 varchar(200)  NOT NULL,
	CONSTRAINT pk_notification_types PRIMARY KEY ( id )
 );

CREATE TABLE order_categories ( 
	id                   serial  NOT NULL,
	name                 varchar(200)  NOT NULL,
	price                numeric(8,2)  ,
	order_date           date  ,
	availability         bool DEFAULT true NOT NULL,
	CONSTRAINT pk_order_categories PRIMARY KEY ( id )
 );

CREATE TABLE rooms ( 
	id                   integer  NOT NULL,
	price                numeric(8,2)  NOT NULL,
	number_of_beds       integer  NOT NULL,
	id_class             integer  NOT NULL,
	floor                integer  NOT NULL,
	CONSTRAINT pk_rooms PRIMARY KEY ( id )
 );

CREATE INDEX idx_rooms ON rooms ( id_class );

CREATE TABLE cooperating_companies ( 
	id                   serial  NOT NULL,
	name                 varchar(300)  NOT NULL,
	phone                varchar(50)  NOT NULL,
	mail                 varchar(50)  ,
	responsible_worker   integer  ,
	CONSTRAINT pk_cooperating_companies PRIMARY KEY ( id )
 );

CREATE INDEX idx_cooperating_companies ON cooperating_companies ( responsible_worker );

CREATE TABLE departments ( 
	id                   serial  NOT NULL,
	name                 varchar(100)  NOT NULL,
	salary               numeric(8,2)  NOT NULL,
	leader               integer  NOT NULL,
	CONSTRAINT pk_sections PRIMARY KEY ( id )
 );

CREATE INDEX idx_sections ON departments ( leader );

CREATE TABLE employees ( 
	id                   serial  NOT NULL,
	name                 varchar(200)  NOT NULL,
	address              varchar(300)  NOT NULL,
	phone                varchar(50)  NOT NULL,
	mail                 varchar(100)  ,
	id_department        integer  NOT NULL,
	bonus                numeric(8,2)  ,
	CONSTRAINT pk_employees PRIMARY KEY ( id )
 );

CREATE INDEX idx_employees ON employees ( id_department );

COMMENT ON COLUMN employees.bonus IS 'Bonus to salary';

CREATE TABLE facilities ( 
	id                   serial  NOT NULL,
	name                 varchar(200)  NOT NULL,
	description          text  ,
	responsible_worker   integer  ,
	CONSTRAINT pk_facilities PRIMARY KEY ( id )
 );

CREATE INDEX idx_facilities ON facilities ( responsible_worker );

CREATE TABLE guests ( 
	id                   serial  NOT NULL,
	name                 varchar(200)  NOT NULL,
	phone                varchar(50)  NOT NULL,
	mail                 varchar(100)  ,
	booker               integer  NOT NULL,
	CONSTRAINT pk_guests PRIMARY KEY ( id )
 );

CREATE INDEX idx_guests ON guests ( booker );

CREATE TABLE notifications ( 
	id                   serial  NOT NULL,
	id_type              integer  ,
	id_department        integer  ,
	priority             integer  ,
	received_time        time  ,
	done                 bool  NOT NULL,
	CONSTRAINT pk_notifications PRIMARY KEY ( id )
 );

CREATE INDEX idx_notifications ON notifications ( id_department );

CREATE INDEX idx_notifications_0 ON notifications ( id_type );

CREATE TABLE orders ( 
	id                   serial  NOT NULL,
	id_guest             integer  NOT NULL,
	received_date        date  ,
	id_order_categories  integer  NOT NULL,
	id_notification      integer  NOT NULL,
	CONSTRAINT pk_orders PRIMARY KEY ( id )
 );

CREATE INDEX idx_orders ON orders ( id_order_categories );

CREATE INDEX idx_orders_0 ON orders ( id_guest );

CREATE INDEX idx_orders_1 ON orders ( id_notification );

CREATE TABLE payments ( 
	id                   serial  NOT NULL,
	id_order             integer  NOT NULL,
	amount               numeric(8,2)  NOT NULL,
	description          text  ,
	cash                 bool  NOT NULL,
	CONSTRAINT pk_payments PRIMARY KEY ( id )
 );

CREATE INDEX idx_payments ON payments ( id_order );

CREATE TABLE photos ( 
	id                   serial  NOT NULL,
	id_facility          integer  NOT NULL,
	filepath             varchar(200)  NOT NULL,
	CONSTRAINT pk_galery PRIMARY KEY ( id )
 );

CREATE INDEX idx_galery ON photos ( id_facility );

CREATE TABLE reservations ( 
	id                   serial  NOT NULL,
	id_room              integer  NOT NULL,
	id_guest             integer  NOT NULL,
	date_from            date  NOT NULL,
	date_to              date  NOT NULL,
	id_notification      integer  NOT NULL,
	CONSTRAINT pk_reservations PRIMARY KEY ( id )
 );

CREATE INDEX idx_reservation ON reservations ( id_room );

CREATE INDEX idx_reservation_0 ON reservations ( id_guest );

CREATE INDEX idx_reservations ON reservations ( id_notification );

ALTER TABLE cooperating_companies ADD CONSTRAINT fk_cooperating_companies_emplyee FOREIGN KEY ( responsible_worker ) REFERENCES employees( id );

COMMENT ON CONSTRAINT fk_cooperating_companies_emplyee ON cooperating_companies IS '';

ALTER TABLE departments ADD CONSTRAINT fk_sections_leader FOREIGN KEY ( leader ) REFERENCES employees( id );

COMMENT ON CONSTRAINT fk_sections_leader ON departments IS '';

ALTER TABLE employees ADD CONSTRAINT fk_employees_sections FOREIGN KEY ( id_department ) REFERENCES departments( id );

COMMENT ON CONSTRAINT fk_employees_sections ON employees IS '';

ALTER TABLE facilities ADD CONSTRAINT fk_facilities_employee FOREIGN KEY ( responsible_worker ) REFERENCES employees( id );

COMMENT ON CONSTRAINT fk_facilities_employee ON facilities IS '';

ALTER TABLE guests ADD CONSTRAINT fk_leaders FOREIGN KEY ( booker ) REFERENCES guests( id );

COMMENT ON CONSTRAINT fk_leaders ON guests IS '';

ALTER TABLE notifications ADD CONSTRAINT fk_notifications_section FOREIGN KEY ( id_department ) REFERENCES departments( id );

COMMENT ON CONSTRAINT fk_notifications_section ON notifications IS '';

ALTER TABLE notifications ADD CONSTRAINT fk_notifications_type FOREIGN KEY ( id_type ) REFERENCES notification_types( id );

COMMENT ON CONSTRAINT fk_notifications_type ON notifications IS '';

ALTER TABLE orders ADD CONSTRAINT fk_orders_categories FOREIGN KEY ( id_order_categories ) REFERENCES order_categories( id );

COMMENT ON CONSTRAINT fk_orders_categories ON orders IS '';

ALTER TABLE orders ADD CONSTRAINT fk_orders_guests FOREIGN KEY ( id_guest ) REFERENCES guests( id );

COMMENT ON CONSTRAINT fk_orders_guests ON orders IS '';

ALTER TABLE orders ADD CONSTRAINT fk_orders_not FOREIGN KEY ( id_notification ) REFERENCES notifications( id );

COMMENT ON CONSTRAINT fk_orders_not ON orders IS '';

ALTER TABLE payments ADD CONSTRAINT fk_payments_orders FOREIGN KEY ( id_order ) REFERENCES orders( id );

COMMENT ON CONSTRAINT fk_payments_orders ON payments IS '';

ALTER TABLE photos ADD CONSTRAINT fk_galery_fac FOREIGN KEY ( id_facility ) REFERENCES facilities( id );

COMMENT ON CONSTRAINT fk_galery_fac ON photos IS '';

ALTER TABLE reservations ADD CONSTRAINT fk_reservation_room FOREIGN KEY ( id_room ) REFERENCES rooms( id );

COMMENT ON CONSTRAINT fk_reservation_room ON reservations IS '';

ALTER TABLE reservations ADD CONSTRAINT fk_reservation_guest FOREIGN KEY ( id_guest ) REFERENCES guests( id );

COMMENT ON CONSTRAINT fk_reservation_guest ON reservations IS '';

ALTER TABLE reservations ADD CONSTRAINT fk_reservations_not FOREIGN KEY ( id_notification ) REFERENCES notifications( id );

COMMENT ON CONSTRAINT fk_reservations_not ON reservations IS '';

ALTER TABLE rooms ADD CONSTRAINT fk_rooms FOREIGN KEY ( id_class ) REFERENCES classes( id );

COMMENT ON CONSTRAINT fk_rooms ON rooms IS '';

