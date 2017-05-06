CREATE SCHEMA hotelsystems;

CREATE TABLE hotelsystems.classes ( 
	id                   serial  NOT NULL,
	name                 varchar(100)  NOT NULL,
	base_price           numeric(8,2)  ,
	CONSTRAINT pk_classes PRIMARY KEY ( id )
 );

COMMENT ON TABLE hotelsystems.classes IS 'Different types of room standard';

COMMENT ON COLUMN hotelsystems.classes.base_price IS 'Base price is only an information for setting price for a particular room.';

CREATE TABLE hotelsystems.notification_types ( 
	id                   serial  NOT NULL,
	name                 varchar(200)  NOT NULL,
	CONSTRAINT pk_notification_types PRIMARY KEY ( id )
 );

CREATE TABLE hotelsystems.order_categories ( 
	id                   serial  NOT NULL,
	name                 varchar(200)  NOT NULL,
	price                numeric(8,2)  ,
	order_date           date  ,
	availability         bool DEFAULT true NOT NULL,
	CONSTRAINT pk_order_categories PRIMARY KEY ( id )
 );

CREATE TABLE hotelsystems.rooms ( 
	id                   integer  NOT NULL,
	price                numeric(8,2)  NOT NULL,
	number_of_beds       integer  NOT NULL,
	id_class             integer  NOT NULL,
	floor                integer  NOT NULL,
	CONSTRAINT pk_rooms PRIMARY KEY ( id )
 );

CREATE INDEX idx_rooms ON hotelsystems.rooms ( id_class );

CREATE TABLE hotelsystems.cooperating_companies ( 
	id                   serial  NOT NULL,
	name                 varchar(300)  NOT NULL,
	phone                varchar(50)  NOT NULL,
	mail                 varchar(50)  ,
	responsible_worker   integer  ,
	CONSTRAINT pk_cooperating_companies PRIMARY KEY ( id )
 );

CREATE INDEX idx_cooperating_companies ON hotelsystems.cooperating_companies ( responsible_worker );

CREATE TABLE hotelsystems.departments ( 
	id                   serial  NOT NULL,
	name                 varchar(100)  NOT NULL,
	salary               numeric(8,2)  NOT NULL,
	leader               integer  NOT NULL,
	CONSTRAINT pk_sections PRIMARY KEY ( id )
 );

CREATE INDEX idx_sections ON hotelsystems.departments ( leader );

CREATE TABLE hotelsystems.employees ( 
	id                   serial  NOT NULL,
	name                 varchar(200)  NOT NULL,
	address              varchar(300)  NOT NULL,
	phone                varchar(50)  NOT NULL,
	mail                 varchar(100)  ,
	id_department        integer  NOT NULL,
	bonus                numeric(8,2)  ,
	CONSTRAINT pk_employees PRIMARY KEY ( id )
 );

CREATE INDEX idx_employees ON hotelsystems.employees ( id_department );

COMMENT ON COLUMN hotelsystems.employees.bonus IS 'Bonus to salary';

CREATE TABLE hotelsystems.facilities ( 
	id                   serial  NOT NULL,
	name                 varchar(200)  NOT NULL,
	description          text  ,
	responsible_worker   integer  ,
	CONSTRAINT pk_facilities PRIMARY KEY ( id )
 );

CREATE INDEX idx_facilities ON hotelsystems.facilities ( responsible_worker );

CREATE TABLE hotelsystems.guests ( 
	id                   serial  NOT NULL,
	name                 varchar(200)  NOT NULL,
	phone                varchar(50)  NOT NULL,
	mail                 varchar(100)  ,
	booker               integer  NOT NULL,
	CONSTRAINT pk_guests PRIMARY KEY ( id )
 );

CREATE INDEX idx_guests ON hotelsystems.guests ( booker );

CREATE TABLE hotelsystems.notifications ( 
	id                   serial  NOT NULL,
	id_type              integer  ,
	id_department        integer  ,
	priority             integer  ,
	received_time        time  ,
	done                 bool  NOT NULL,
	CONSTRAINT pk_notifications PRIMARY KEY ( id )
 );

CREATE INDEX idx_notifications ON hotelsystems.notifications ( id_department );

CREATE INDEX idx_notifications_0 ON hotelsystems.notifications ( id_type );

CREATE TABLE hotelsystems.orders ( 
	id                   serial  NOT NULL,
	id_guest             integer  NOT NULL,
	received_date        date  ,
	id_order_categories  integer  NOT NULL,
	id_notification      integer  NOT NULL,
	CONSTRAINT pk_orders PRIMARY KEY ( id )
 );

CREATE INDEX idx_orders ON hotelsystems.orders ( id_order_categories );

CREATE INDEX idx_orders_0 ON hotelsystems.orders ( id_guest );

CREATE INDEX idx_orders_1 ON hotelsystems.orders ( id_notification );

CREATE TABLE hotelsystems.payments ( 
	id                   serial  NOT NULL,
	id_order             integer  NOT NULL,
	amount               numeric(8,2)  NOT NULL,
	description          text  ,
	cash                 bool  NOT NULL,
	CONSTRAINT pk_payments PRIMARY KEY ( id )
 );

CREATE INDEX idx_payments ON hotelsystems.payments ( id_order );

CREATE TABLE hotelsystems.photos ( 
	id                   serial  NOT NULL,
	id_facility          integer  NOT NULL,
	filepath             varchar(200)  NOT NULL,
	CONSTRAINT pk_galery PRIMARY KEY ( id )
 );

CREATE INDEX idx_galery ON hotelsystems.photos ( id_facility );

CREATE TABLE hotelsystems.reservations ( 
	id                   serial  NOT NULL,
	id_room              integer  NOT NULL,
	id_guest             integer  NOT NULL,
	date_from            date  NOT NULL,
	date_to              date  NOT NULL,
	id_notification      integer  NOT NULL,
	CONSTRAINT pk_reservations PRIMARY KEY ( id )
 );

CREATE INDEX idx_reservation ON hotelsystems.reservations ( id_room );

CREATE INDEX idx_reservation_0 ON hotelsystems.reservations ( id_guest );

CREATE INDEX idx_reservations ON hotelsystems.reservations ( id_notification );

ALTER TABLE hotelsystems.cooperating_companies ADD CONSTRAINT fk_cooperating_companies_emplyee FOREIGN KEY ( responsible_worker ) REFERENCES hotelsystems.employees( id );

COMMENT ON CONSTRAINT fk_cooperating_companies_emplyee ON hotelsystems.cooperating_companies IS '';

ALTER TABLE hotelsystems.departments ADD CONSTRAINT fk_sections_leader FOREIGN KEY ( leader ) REFERENCES hotelsystems.employees( id );

COMMENT ON CONSTRAINT fk_sections_leader ON hotelsystems.departments IS '';

ALTER TABLE hotelsystems.employees ADD CONSTRAINT fk_employees_sections FOREIGN KEY ( id_department ) REFERENCES hotelsystems.departments( id );

COMMENT ON CONSTRAINT fk_employees_sections ON hotelsystems.employees IS '';

ALTER TABLE hotelsystems.facilities ADD CONSTRAINT fk_facilities_employee FOREIGN KEY ( responsible_worker ) REFERENCES hotelsystems.employees( id );

COMMENT ON CONSTRAINT fk_facilities_employee ON hotelsystems.facilities IS '';

ALTER TABLE hotelsystems.guests ADD CONSTRAINT fk_leaders FOREIGN KEY ( booker ) REFERENCES hotelsystems.guests( id );

COMMENT ON CONSTRAINT fk_leaders ON hotelsystems.guests IS '';

ALTER TABLE hotelsystems.notifications ADD CONSTRAINT fk_notifications_section FOREIGN KEY ( id_department ) REFERENCES hotelsystems.departments( id );

COMMENT ON CONSTRAINT fk_notifications_section ON hotelsystems.notifications IS '';

ALTER TABLE hotelsystems.notifications ADD CONSTRAINT fk_notifications_type FOREIGN KEY ( id_type ) REFERENCES hotelsystems.notification_types( id );

COMMENT ON CONSTRAINT fk_notifications_type ON hotelsystems.notifications IS '';

ALTER TABLE hotelsystems.orders ADD CONSTRAINT fk_orders_categories FOREIGN KEY ( id_order_categories ) REFERENCES hotelsystems.order_categories( id );

COMMENT ON CONSTRAINT fk_orders_categories ON hotelsystems.orders IS '';

ALTER TABLE hotelsystems.orders ADD CONSTRAINT fk_orders_guests FOREIGN KEY ( id_guest ) REFERENCES hotelsystems.guests( id );

COMMENT ON CONSTRAINT fk_orders_guests ON hotelsystems.orders IS '';

ALTER TABLE hotelsystems.orders ADD CONSTRAINT fk_orders_not FOREIGN KEY ( id_notification ) REFERENCES hotelsystems.notifications( id );

COMMENT ON CONSTRAINT fk_orders_not ON hotelsystems.orders IS '';

ALTER TABLE hotelsystems.payments ADD CONSTRAINT fk_payments_orders FOREIGN KEY ( id_order ) REFERENCES hotelsystems.orders( id );

COMMENT ON CONSTRAINT fk_payments_orders ON hotelsystems.payments IS '';

ALTER TABLE hotelsystems.photos ADD CONSTRAINT fk_galery_fac FOREIGN KEY ( id_facility ) REFERENCES hotelsystems.facilities( id );

COMMENT ON CONSTRAINT fk_galery_fac ON hotelsystems.photos IS '';

ALTER TABLE hotelsystems.reservations ADD CONSTRAINT fk_reservation_room FOREIGN KEY ( id_room ) REFERENCES hotelsystems.rooms( id );

COMMENT ON CONSTRAINT fk_reservation_room ON hotelsystems.reservations IS '';

ALTER TABLE hotelsystems.reservations ADD CONSTRAINT fk_reservation_guest FOREIGN KEY ( id_guest ) REFERENCES hotelsystems.guests( id );

COMMENT ON CONSTRAINT fk_reservation_guest ON hotelsystems.reservations IS '';

ALTER TABLE hotelsystems.reservations ADD CONSTRAINT fk_reservations_not FOREIGN KEY ( id_notification ) REFERENCES hotelsystems.notifications( id );

COMMENT ON CONSTRAINT fk_reservations_not ON hotelsystems.reservations IS '';

ALTER TABLE hotelsystems.rooms ADD CONSTRAINT fk_rooms FOREIGN KEY ( id_class ) REFERENCES hotelsystems.classes( id );

COMMENT ON CONSTRAINT fk_rooms ON hotelsystems.rooms IS '';

