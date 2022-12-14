drop table shipPosition;
drop table shipment;
drop table shipTrip;
drop table EnergyPower;
drop table positionInVehicule;
drop table containerInCargoManifest;
drop table port;
drop table placeLocation;
drop table country;
drop table continent;
drop table container;
drop table ship;
drop table cargomanifest;


create table continent(
	continent_id integer GENERATED BY DEFAULT AS IDENTITY(start with 1 increment by 1) constraint pk_continent PRIMARY KEY NOT NULL,
	continent_name varchar(30) NOT NULL
	);

create table country(
	country_name varchar(70) constraint pk_country PRIMARY KEY NOT NULL,
	continent_id integer NOT NULL,
    constraint fk_continent_country FOREIGN KEY (continent_id) references continent(continent_id)
	);

create table placeLocation(
    location_id integer GENERATED BY DEFAULT AS IDENTITY(start with 1 increment by 1) constraint pk_placeLocation PRIMARY KEY NOT NULL,
	locationLatitude numeric(11,9) NOT NULL ,
	locationLongitude numeric(12,9) NOT NULL ,
	country varchar(70) NOT NULL,
    
    constraint chk_longitude CHECK(locationLongitude >= -180 AND locationLongitude <= 180),
    constraint chk_latitude CHECK(locationLatitude >= -90 AND locationLatitude <= 90),
	constraint fk_placeLocation_country FOREIGN KEY (country) references country(country_name)
);

create table port(
	port_id integer constraint pk_port PRIMARY KEY,
	name varchar(40) NOT NULL,
    location_id integer not null,
	constraint fk_port_placeLocation FOREIGN KEY (location_id) references placeLocation(location_id)
);

create table ship(
	mmsi integer constraint pk_ship PRIMARY KEY NOT NULL,
	vesselTypeId integer NOT NULL,
	imo varchar(10) UNIQUE NOT NULL ,
	callSign varchar(10) UNIQUE NOT NULL,
	shipName varchar(30) NOT NULL,
	currentCapacity varchar(10),
	draft numeric(5,2) NOT NULL ,
    length numeric(5,2) NOT NULL,
	width numeric(5,2) NOT NULL ,

	constraint chk_imo check(imo like 'IMO%'),
	constraint chk_draft check(draft > 0)
);


create table cargoManifest(
	cargoManifest_id integer constraint pk_cargoManifest PRIMARY KEY
);

create table shipTrip(
    shiptrip_id integer GENERATED BY DEFAULT AS IDENTITY(start with 1 increment by 1) constraint pk_ship_trip PRIMARY KEY,
    mmsi integer not null,
    departure_location integer not null,
    arrival_location integer not null,
    loading_cargo_id integer not null,
    unloading_cargo_id integer,
    est_departure_date date not null,
    est_arrival_date date not null,
    real_departure_date date,
    real_arrival_date date,

    constraint fk_shiptrip_loading_cargoManifest FOREIGN KEY (loading_cargo_id) references cargoManifest(cargoManifest_id),
    constraint fk_shiptrip_unloading_cargoManifest FOREIGN KEY (unloading_cargo_id) references cargoManifest(cargoManifest_id),
    constraint fk_shiptrip_ship FOREIGN KEY (mmsi) references ship(mmsi),
    constraint fk_shiptrip_arrival_port FOREIGN KEY (arrival_location) references port(port_id),
    constraint fk_shiptrip_depart_port FOREIGN KEY (departure_location) references port(port_id)
);

create table shipPosition(
    shipPosition_id integer GENERATED ALWAYS AS IDENTITY(start with 1 increment by 1) not null constraint pk_shipposition PRIMARY KEY,
	baseDateTime date NOT NULL,
	latitude numeric(6,4) NOT NULL ,
	longitude numeric(7,4) NOT NULL,
	mmsi integer NOT NULL,
	sog numeric(5,2) NOT NULL,
	cog numeric(5,2) NOT NULL check(cog >=0 AND cog <= 359),
	heading numeric(5,2) NOT NULL check((heading >=0 AND heading <= 359) OR heading = 511),
	transceiver varchar(15) NOT NULL,

	constraint chk_latitude_ship_position check((latitude >= -90 AND latitude <= 90) OR latitude = 91),
	constraint chk_longitude_ship_position check((longitude >= -180 AND longitude <= 180) OR longitude = 181),
	constraint fk_shipPosition_ship FOREIGN KEY (mmsi) references ship(mmsi)
);

create table energyPower(
	numEnergyGenerators integer,
	mmsi integer NOT NULL,
	generatorsPower numeric(5,2),

	constraint fk_energyPower_ship FOREIGN KEY (mmsi) references ship(mmsi),
	constraint pk_energyPower PRIMARY KEY (numEnergyGenerators, mmsi)
);

create table container(
	container_id integer constraint pk_container PRIMARY KEY NOT NULL,
	payload numeric(5,2) NOT NULL,
	tare numeric(5,2) NOT NULL,
	gross numeric(5,2) NOT NULL,
	isoCode varchar(4) NOT NULL,

	constraint chk_payload check(payload >= 0),
	constraint chk_tare check(tare >= 0),
	constraint chk_gross check(gross >= 0)
);
create table shipment(
    shipment_id integer GENERATED ALWAYS AS IDENTITY(start with 1 increment by 1) not null constraint shipment_pk PRIMARY KEY,
    container_id integer not null,
    shiptrip_id integer not null,

    constraint fk_shipment_container FOREIGN KEY (container_id) references container(container_id),
    constraint fk_shipment_shiptrip FOREIGN KEY (shiptrip_id) references shiptrip(shiptrip_id)
);
create table positionInVehicule(
	cargoManifest_id integer NOT NULL,
	container_id integer NOT NULL,
	containerPositionX integer NOT NULL,
	containerPositionY integer NOT NULL,
	containerPositionZ integer NOT NULL,

	constraint fk_positionInVehicule_cargoManifest FOREIGN KEY (cargoManifest_id) references cargoManifest(cargoManifest_id),
	constraint fk_positionInVehicule_container FOREIGN KEY (container_id) references container(container_id),
	constraint pk_positionInVehicule PRIMARY KEY (cargoManifest_id, container_id)
);


create table containerInCargoManifest(
	container_id integer NOT NULL,
	cargoManifest_id integer NOT NULL,
    temperature_kept numeric(6,2),

	constraint pk_containerInCargoManifest PRIMARY KEY (container_id, cargoManifest_id),
	constraint fk_containerInCargoManifest_container FOREIGN KEY (container_id) references container(container_id),
	constraint fk_containerInCargoManifest_cargoManifest FOREIGN KEY (cargoManifest_id) references cargoManifest(cargoManifest_id)
);
