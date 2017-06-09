CREATE TABLE notification_type (
	id                   serial  NOT NULL,
	name                 varchar(200)  NOT NULL,
	CONSTRAINT pk_notification_types PRIMARY KEY ( id )
 );

CREATE TABLE order_category ( 
	id                   serial  NOT NULL,
	name                 varchar(200)  NOT NULL,
	price                numeric(8,2)  ,
	order_date           date  ,
	availability         bool DEFAULT true NOT NULL,
	CONSTRAINT pk_order_categories PRIMARY KEY ( id )
 );

CREATE TABLE photo ( 
	id                   serial  NOT NULL,
	filepath             varchar(200)  NOT NULL,
	CONSTRAINT pk_galery PRIMARY KEY ( id )
 );

CREATE TABLE standard ( 
	id                   serial  NOT NULL,
	name                 varchar(100)  NOT NULL,
	base_price           numeric(8,2)  ,
	CONSTRAINT pk_classes PRIMARY KEY ( id )
 );

COMMENT ON TABLE standard IS 'Different types of room standard';

COMMENT ON COLUMN standard.base_price IS 'Base price is only an information for setting price for a particular room.';

CREATE TABLE "user" ( 
	id                   serial  NOT NULL,
	email                varchar(100)  NOT NULL,
	pass_hash            varchar(100)  ,
	is_admin             bool  ,
	CONSTRAINT pk_users PRIMARY KEY ( id )
 );

CREATE TABLE room ( 
	id                   integer  NOT NULL,
	price                numeric(8,2)  NOT NULL,
	number_of_beds       integer  NOT NULL,
	id_standard          integer  NOT NULL,
	floor                integer  NOT NULL,
	id_photo             integer  ,
	CONSTRAINT pk_rooms PRIMARY KEY ( id )
 );

CREATE INDEX idx_rooms ON room ( id_standard );

CREATE INDEX idx_room ON room ( id_photo );

CREATE TABLE "session" ( 
	id                   serial  NOT NULL,
	session_id           integer  NOT NULL,
	user_id              integer  NOT NULL,
	expiration_date      date  NOT NULL,
	CONSTRAINT pk_session PRIMARY KEY ( id )
 );

CREATE INDEX idx_session ON "session" ( user_id );

CREATE TABLE cooperating_company ( 
	id                   serial  NOT NULL,
	name                 varchar(300)  NOT NULL,
	phone                varchar(50)  NOT NULL,
	email                varchar(50)  ,
	responsible_worker   integer  ,
	CONSTRAINT pk_cooperating_companies PRIMARY KEY ( id )
 );

CREATE INDEX idx_cooperating_companies ON cooperating_company ( responsible_worker );

CREATE TABLE department ( 
	id                   serial  NOT NULL,
	name                 varchar(100)  NOT NULL,
	salary               numeric(8,2)  NOT NULL,
	leader               integer  NOT NULL,
	CONSTRAINT pk_sections PRIMARY KEY ( id )
 );

CREATE INDEX idx_sections ON department ( leader );

CREATE TABLE employee ( 
	id                   serial  NOT NULL,
	name                 varchar(200)  NOT NULL,
	address              varchar(300)  NOT NULL,
	phone                varchar(50)  NOT NULL,
	email                varchar(100)  ,
	id_department        integer  NOT NULL,
	bonus                numeric(8,2)  ,
	CONSTRAINT pk_employees PRIMARY KEY ( id )
 );

CREATE INDEX idx_employees ON employee ( id_department );

COMMENT ON COLUMN employee.bonus IS 'Bonus to salary';

CREATE TABLE facility ( 
	id                   serial  NOT NULL,
	name                 varchar(200)  NOT NULL,
	description          text  ,
	responsible_worker   integer  ,
	id_photo             integer  ,
	CONSTRAINT pk_facilities PRIMARY KEY ( id )
 );

CREATE INDEX idx_facilities ON facility ( responsible_worker );

CREATE INDEX idx_facility ON facility ( id_photo );

CREATE TABLE guest ( 
	id                   serial  NOT NULL,
	name                 varchar(200)  NOT NULL,
	phone                varchar(50)  NOT NULL,
	email                varchar(100)  ,
	booker               integer  NOT NULL,
	user_id              integer  ,
	CONSTRAINT pk_guests PRIMARY KEY ( id )
 );

CREATE INDEX idx_guests ON guest ( booker );

CREATE INDEX idx_guests_0 ON guest ( user_id );

CREATE TABLE notification ( 
	id                   serial  NOT NULL,
	id_type              integer  ,
	id_department        integer  ,
	priority             integer  ,
	received_time        time  ,
	done                 bool  NOT NULL,
	CONSTRAINT pk_notifications PRIMARY KEY ( id )
 );

CREATE INDEX idx_notifications ON notification ( id_department );

CREATE INDEX idx_notifications_0 ON notification ( id_type );

CREATE TABLE "order" ( 
	id                   serial  NOT NULL,
	id_guest             integer  NOT NULL,
	received_date        date  ,
	id_order_category    integer  NOT NULL,
	id_notification      integer  NOT NULL,
	CONSTRAINT pk_orders PRIMARY KEY ( id )
 );

CREATE INDEX idx_orders ON "order" ( id_order_category );

CREATE INDEX idx_orders_0 ON "order" ( id_guest );

CREATE INDEX idx_orders_1 ON "order" ( id_notification );

CREATE TABLE payment ( 
	id                   serial  NOT NULL,
	id_order             integer  NOT NULL,
	amount               numeric(8,2)  NOT NULL,
	description          text  ,
	cash                 bool  NOT NULL,
	CONSTRAINT pk_payments PRIMARY KEY ( id )
 );

CREATE INDEX idx_payments ON payment ( id_order );

CREATE TABLE reservation ( 
	id                   serial  NOT NULL,
	id_room              integer  NOT NULL,
	id_guest             integer  NOT NULL,
	date_from            date  NOT NULL,
	date_to              date  NOT NULL,
	id_notification      integer  NOT NULL,
	CONSTRAINT pk_reservations PRIMARY KEY ( id )
 );

CREATE INDEX idx_reservation ON reservation ( id_room );

CREATE INDEX idx_reservation_0 ON reservation ( id_guest );

CREATE INDEX idx_reservations ON reservation ( id_notification );

ALTER TABLE cooperating_company ADD CONSTRAINT fk_cooperating_companies_emplyee FOREIGN KEY ( responsible_worker ) REFERENCES employee( id );

COMMENT ON CONSTRAINT fk_cooperating_companies_emplyee ON cooperating_company IS '';

ALTER TABLE department ADD CONSTRAINT fk_sections_leader FOREIGN KEY ( leader ) REFERENCES employee( id );

COMMENT ON CONSTRAINT fk_sections_leader ON department IS '';

ALTER TABLE employee ADD CONSTRAINT fk_employees_sections FOREIGN KEY ( id_department ) REFERENCES department( id );

COMMENT ON CONSTRAINT fk_employees_sections ON employee IS '';

ALTER TABLE facility ADD CONSTRAINT fk_facilities_employee FOREIGN KEY ( responsible_worker ) REFERENCES employee( id );

COMMENT ON CONSTRAINT fk_facilities_employee ON facility IS '';

ALTER TABLE facility ADD CONSTRAINT fk_facility_photo FOREIGN KEY ( id_photo ) REFERENCES photo( id );

COMMENT ON CONSTRAINT fk_facility_photo ON facility IS '';

ALTER TABLE guest ADD CONSTRAINT fk_leaders FOREIGN KEY ( booker ) REFERENCES guest( id );

COMMENT ON CONSTRAINT fk_leaders ON guest IS '';

ALTER TABLE guest ADD CONSTRAINT fk_guests_users FOREIGN KEY ( user_id ) REFERENCES "user"( id );

COMMENT ON CONSTRAINT fk_guests_users ON guest IS '';

ALTER TABLE notification ADD CONSTRAINT fk_notifications_section FOREIGN KEY ( id_department ) REFERENCES department( id );

COMMENT ON CONSTRAINT fk_notifications_section ON notification IS '';

ALTER TABLE notification ADD CONSTRAINT fk_notifications_type FOREIGN KEY ( id_type ) REFERENCES notification_type( id );

COMMENT ON CONSTRAINT fk_notifications_type ON notification IS '';

ALTER TABLE "order" ADD CONSTRAINT fk_orders_categories FOREIGN KEY ( id_order_category ) REFERENCES order_category( id );

COMMENT ON CONSTRAINT fk_orders_categories ON "order" IS '';

ALTER TABLE "order" ADD CONSTRAINT fk_orders_guests FOREIGN KEY ( id_guest ) REFERENCES guest( id );

COMMENT ON CONSTRAINT fk_orders_guests ON "order" IS '';

ALTER TABLE "order" ADD CONSTRAINT fk_orders_not FOREIGN KEY ( id_notification ) REFERENCES notification( id );

COMMENT ON CONSTRAINT fk_orders_not ON "order" IS '';

ALTER TABLE payment ADD CONSTRAINT fk_payments_orders FOREIGN KEY ( id_order ) REFERENCES "order"( id );

COMMENT ON CONSTRAINT fk_payments_orders ON payment IS '';

ALTER TABLE reservation ADD CONSTRAINT fk_reservation_room FOREIGN KEY ( id_room ) REFERENCES room( id );

COMMENT ON CONSTRAINT fk_reservation_room ON reservation IS '';

ALTER TABLE reservation ADD CONSTRAINT fk_reservation_guest FOREIGN KEY ( id_guest ) REFERENCES guest( id );

COMMENT ON CONSTRAINT fk_reservation_guest ON reservation IS '';

ALTER TABLE reservation ADD CONSTRAINT fk_reservations_not FOREIGN KEY ( id_notification ) REFERENCES notification( id );

COMMENT ON CONSTRAINT fk_reservations_not ON reservation IS '';

ALTER TABLE room ADD CONSTRAINT fk_room_standard FOREIGN KEY ( id_standard ) REFERENCES standard( id );

COMMENT ON CONSTRAINT fk_room_standard ON room IS '';

ALTER TABLE room ADD CONSTRAINT fk_room_photo FOREIGN KEY ( id_photo ) REFERENCES photo( id );

COMMENT ON CONSTRAINT fk_room_photo ON room IS '';

ALTER TABLE "session" ADD CONSTRAINT fk_session_user FOREIGN KEY ( user_id ) REFERENCES "user"( id );

COMMENT ON CONSTRAINT fk_session_user ON "session" IS '';

