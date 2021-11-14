create table country(
	country_name varchar(70) constraint pk_country PRIMARY KEY NOT NULL,
	continent varchar(30) NOT NULL
	);

create table placeLocation(
	locationLatitude numeric(6,4) NOT NULL ,
	locationLongitude numeric(7,4) NOT NULL ,
	country varchar(70) NOT NULL,

    constraint chk_longitude CHECK(locationLongitude >= -180 AND locationLongitude <= 180),
    constraint chk_latitude CHECK(locationLatitude >= -90 AND locationLatitude <= 90),
	constraint pk_placelocation_coordinates PRIMARY KEY (locationLatitude, locationLongitude),
	constraint fk_placeLocation_country FOREIGN KEY (country) references country(country_name)
);

create table port(
	port_id integer constraint pk_port PRIMARY KEY,
	name varchar(40) NOT NULL,
	locationLongitude numeric(6,4) NOT NULL,
	locationLatitude numeric(7,4) NOT NULL,

	constraint fk_port_placeLocation FOREIGN KEY (locationLongitude, locationLatitude) references placeLocation(locationLongitude, locationLatitude)
);


create table vesselType(
	vesselTypeId integer constraint pk_vesselType PRIMARY KEY NOT NULL,
	length numeric(5,2) NOT NULL,
	width numeric(5,2) NOT NULL ,
	maxCapacity numeric(6,2)
);

create table ship(
	mmsi integer constraint pk_ship PRIMARY KEY NOT NULL,
	vesselTypeId integer UNIQUE NOT NULL,
	imo varchar(10) UNIQUE NOT NULL ,
	callSign varchar(4) UNIQUE NOT NULL,
	shipName varchar(30) NOT NULL,
	currentCapacity numeric(6,2),
	draft numeric(5,2) NOT NULL ,

	constraint chk_imo check(imo like 'IMO%'),
	constraint chk_draft check(draft > 0),
	constraint fk_ship_vesselType FOREIGN KEY (vesselTypeId) references vesselType(vesselTypeId)
);


create table cargoManifest(
	cargoManifest_id integer constraint pk_cargoManifest PRIMARY KEY,
	port_id integer NOT NULL,
	mmsi integer NOT NULL,
	typeOfTransport varchar(10) NOT NULL,
	status varchar(12) NOT NULL ,

    constraint chk_status check(status like 'loading' OR status like 'unloading'),
	constraint fk_cargoManifest_port FOREIGN KEY (port_id) references port(port_id),
	constraint fk_cargoManifest_ship FOREIGN KEY (mmsi) references ship(mmsi)
);


create table shipPosition(
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
	constraint fk_shipPosition_ship FOREIGN KEY (mmsi) references ship(mmsi),
	constraint pk_shipPosition PRIMARY KEY (baseDateTime, latitude, longitude)
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

create table refrigeration(
	container_id integer NOT NULL,
	temperatureKept numeric(5,2),

	constraint fk_regrigeration_container FOREIGN KEY (container_id) references container(container_id),
	constraint pk_refrigeration PRIMARY KEY (container_id, temperatureKept)
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

	constraint pk_containerInCargoManifest PRIMARY KEY (container_id, cargoManifest_id),
	constraint fk_containerInCargoManifest_container FOREIGN KEY (container_id) references container(container_id),
	constraint fk_containerInCargoManifest_cargoManifest FOREIGN KEY (cargoManifest_id) references cargoManifest(cargoManifest_id)

);
