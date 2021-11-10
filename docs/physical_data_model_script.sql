create table vesselType(
	vesselTypeId integer constraint pk_vesselType PRIMARY KEY
	length number(5,2)
	width number(5,2)
	maxCapacity number(6,2)
);

create table ship(
	mmsi integer constraint pk_ship PRIMARY KEY
	vesselTypeId integer
	imo varchar(10)
	callSign varchar(4)
	shipName varchar(30)
	currentCapacity number(6,2)
	draft: number(5,2)
	constraint fk_ship_vesselType FOREIGN KEY (vesselTypeId) references vesselType(vesselTypeId)
);

create table shipPosition(
	baseDateTime date
	latitude number(6,4)
	longitude number(7,4)
	mmsi integer
	sog number(5,2)
	cog number(5,2)
	heading number(5,2)
	transceiver varchar(15)

	constraint fk_shipPosition_ship FOREIGN KEY (mmsi) references ship(mmsi)
	constraint pk_shipPosition PRIMARY KEY (baseDateTime, latitude, longitude)
);

create table energyPower(
	numEnergyGenerators integer
	mmsi
	generatorsPower number(5,2)

	constraint fk_energyPower_ship FOREIGN KEY (mmsi) references ship(mmsi)
	constrait pk_energyPower PRIMARY KEY (numEnergyGenerators, mmsi)
);

create table container(
	container_id integer constraint pk_container PRIMARY KEY
	payload number(5,2)
	tare number(5,2)
	gross number(5,2)
	isoCode varchar(4)
);

create table refrigeration(
	container_id integer
	temperatureKept number(5,2)

	constaint fk_regrigeration_container FOREIGN KEY (container_id) references container(container_id)
);

create table positionInVehicule(
	cargoManifest_id integer
	container_id integer
	containerPositionX integer
	containerPositionY integer
	containerPositionZ integer

	constaint fk_positionInVehicule_cargoManifest FOREIGN KEY (cargoManifest_id) references cargoManifest(cargoManifest_id)
	constaint fk_positionInVehicule_container FOREIGN KEY (container_id) references container(container_id)
	constraint pk_positionInVehicule PRIMARY KEY (cargoManifest_id, container_id)
);


create table cargoManifest(
	cargoManifest_id integer
	port_id integer
	mmsi integer
	typeOfTransport varchar(10)
	status varchar(12)

	constaint fk_cargoManifest_port FOREIGN KEY (port_id) references port(port_id)
	constaint fk_cargoManifest_ship FOREIGN KEY (mmsi) references ship(mmsi)
	constraint pk_cargoManifest PRIMARY KEY (cargoManifest_id, port_id)
);

create table containerInCargoManifest(
	container_id integer
	cargoManifest_id integer

	constraint fk_containerInCargoManifest_container FOREIGN KEY (container_id) references container(container_id)
	constraint fk_containerInCargoManifest_cargoManifest FOREING KEY (cargoManifest_id) references cargoManifest(cargoManifest_id)
	constraint pk_containerInCargoManifest PRIMARY KEY (container_id, cargoManifest_id)
)

create table port(
	port_id integer constraint pk_port PRIMARY KEY
	name varchar(40)
	locationLongitude number(6,4)
	locationLatitude number(7,4)

	constaint fk_port_placeLocation FOREIGN KEY (locationLongitude, locationLatitude) references placeLocation(locationLongitude, locationLatitude)
)

create table placeLocation(
	locationLatitude number(6,4)
	locationLongitude number(7,4)
	country varchar(70)

	constraint pk_placelocation_coordinates PRIMARY KEY (localtionLatitude, locationLongitude)
	constraint fk_placeLocation_country FOREING KEY (country) references country(country_name)
)

create table country(
	country_name varchar(70) constraint pk_country PRIMARY KEY
	continent varchar(30)
)