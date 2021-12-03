--GET_MAX_CAPACITY
--SHIP STORE
create or replace function get_max_capacity(f_cargoManifest_id cargoManifest.cargoManifest_id%type) return integer
is
f_mmsi shipTrip.mmsi%type;
f_max_capacity integer;
begin
select mmsi into f_mmsi
from shipTrip
where loading_cargo_id = f_cargoManifest_id OR unloading_cargo_id = f_cargoManifest_id;
select currentCapacity into f_max_capacity
from ship
where mmsi = f_mmsi;
return (f_max_capacity);
exception
when no_data_found then
return 0;
end;

--GET_NUM_CONTAINERS_PER_CARGO_MANIFEST
--CARGO MANIFEST STORE
create or replace function get_num_containers_per_cargoManifest(f_cargoManifest_id cargoManifest.cargoManifest_id%type) return integer
is
f_num_containers_per_cargoManifest integer;
begin
select count(*) into f_num_containers_per_cargoManifest
from containerInCargoManifest
where cargoManifest_id = f_cargoManifest_id;
return (f_num_containers_per_cargoManifest);
exception
when no_data_found then
return 0;
end;

--GET_EST_DEPARTURE_DATE_FROM_SHIP_TRIP
--SHIP TRIP STORE
create or replace function get_est_departure_date_from_ship_trip(f_cargoManifest_id cargoManifest.cargoManifest_id%type) return shipTrip.est_departure_date%type
is
f_shiptrip_id shipTrip.shiptrip_id%type;
f_est_departure_date shipTrip.est_departure_date%type;
begin
select shiptrip_id into f_shiptrip_id
from shipTrip
where loading_cargo_id = f_cargoManifest_id OR unloading_cargo_id = f_cargoManifest_id;
select est_departure_date into f_est_departure_date
from shipTrip
where shiptrip_id = f_shiptrip_id;
return f_est_departure_date;
end;

--GET_INITIAL_NUM_CONTAINERS_PER_SHIP_TRIP
--SHIP TRIP STORE
create or replace function get_initial_num_containers_per_ship_trip(f_cargoManifest_id cargoManifest.cargoManifest_id%type,
f_est_departure_date shipTrip.est_departure_date%type) return integer --est date como parametro
is
f_comp_shiptrip_id shipTrip.shiptrip_id%type;
f_comp_loading_cargo_id shipTrip.loading_cargo_id%type;
f_comp_unloading_cargo_id shipTrip.unloading_cargo_id%type;
f_initial_num_containers_per_ship_trip integer;
cursor shipTrips
is
select shiptrip_id
from shipTrip
where est_departure_date < f_est_departure_date; --o f_est_departure_date ainda está vazio??
begin
select loading_cargo_id, unloading_cargo_id into f_comp_loading_cargo_id, f_comp_unloading_cargo_id
from shipTrip
where shiptrip_id = f_comp_shiptrip_id;
loop
fetch shipTrips into f_comp_shiptrip_id;
exit when shipTrips%notfound;
select loading_cargo_id, unloading_cargo_id into f_comp_loading_cargo_id, f_comp_unloading_cargo_id
from shipTrip
where shiptrip_id = f_comp_shiptrip_id;
f_initial_num_containers_per_ship_trip := f_initial_num_containers_per_ship_trip + get_num_containers_per_cargoManifest(f_comp_loading_cargo_id) - get_num_containers_per_cargoManifest(f_comp_unloading_cargo_id);
end loop;
return f_initial_num_containers_per_ship_trip;
exception
when no_data_found then
return 0;
end;

--GET_ADDED_REMOVED_CONTAINERS_SHIP_TRIP_MOMENT
--SHIP TRIP STORE
create or replace function get_added_removed_containers_ship_trip_moment(f_cargoManifest_id cargoManifest.cargoManifest_id%type) return integer --est date como parametro
is
f_shiptrip_id shipTrip.shiptrip_id%type;
f_loading_cargo_id shipTrip.loading_cargo_id%type;
f_unloading_cargo_id shipTrip.unloading_cargo_id%type;
f_num_added_removed_containers_ship_trip_moment integer;
begin
select shiptrip_id into f_shiptrip_id
from shipTrip
where loading_cargo_id=f_cargoManifest_id or unloading_cargo_id=f_cargoManifest_id;
select loading_cargo_id, unloading_cargo_id into f_loading_cargo_id, f_unloading_cargo_id
from shipTrip
where shiptrip_id = f_shiptrip_id;
f_num_added_removed_containers_ship_trip_moment := f_num_added_removed_containers_ship_trip_moment + get_num_containers_per_cargoManifest(f_loading_cargo_id);
if f_unloading_cargo_id=f_cargoManifest_id then
f_num_added_removed_containers_ship_trip_moment := f_num_added_removed_containers_ship_trip_moment + get_num_containers_per_cargoManifest(f_unloading_cargo_id);
end if;
return f_num_added_removed_containers_ship_trip_moment;
exception
when no_data_found then
return 0;
end;