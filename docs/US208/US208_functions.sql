--GET_MAX_CAPACITY
create or replace function get_max_capacity(f_cargoManifest_id cargoManifest.cargoManifest_id%type) return integer
is
f_mmsi shipTrip.mmsi%type;
f_max_capacity integer;
begin
select mmsi into f_mmsi
from shipTrip
where loading_cargo_id = f_cargoManifest_id OR unloading_cargo_id = f_cargoManifest_id;
select current_capacity into f_max_capacity
from ship
where mmsi = f_mmsi;
return (f_max_capacity);
exception
when no_data_found then
return 0;
end;

--GET_NUM_CONTAINERS_PER_CARGO_MANIFEST
create or replace function get_num_containers_per_cargoManifest(f_cargoManifest_id cargoManifest.cargoManifest_id%type) return integer
is
f_num_containers_per_cargoManifest integer;
begin
select count(*) into f_num_containers_per_cargoManifest
from containerInCargoManifest
where cargoManifest_id = f_cargoManifest_id
return (f_num_containers_per_cargoManifest);
exception
when no_data_found then
return 0;
end;

--GET_INITIAL_NUM_CONTAINERS_PER_SHIP_TRIP
create or replace function get_initial_num_containers_per_ship_trip(f_cargoManifest_id cargoManifest.cargoManifest_id%type) return integer
is
f_shiptrip_id shipTrip.shiptrip_id%type;
f_est_departure_date shipTrip.est_departure_date%type;
--f_comp_shiptrip_id shipTrip.shiptrip_id%type;
--f_comp_loading_cargo_id shipTrip.loading_cargo_id%type;
--f_comp_unloading_cargo_id shipTrip.unloading_cargo_id%type;
f_initial_num_containers_per_ship_trip integer;
begin
select shiptrip_id into f_shiptrip_id
from shipTrip
where loading_cargo_id = f_cargoManifest_id OR unloading_cargo_id = f_cargoManifest_id;
select est_departure_date into f_est_departure_date
from shipTrip
where shiptrip_id = f_shiptrip_id;
--select loading_cargo_id, unloading_cargo_id into f_comp_loading_cargo_id, f_comp_unloading_cargo_id
--from shipTrip
--where shiptrip_id = f_comp_shiptrip_id;
--somar num do loading e subtrair num do unloading
-- !!! contar tudo antes da shipTrip !!!

--for shipTrips com est_departure_date anterior ao f_est_departure_date da shipTrip em questão,
--somar a f_initial_num_containers_per_ship_trip o valor do get_num_containers_per_cargoManifest do loading_cargo_id e
--subtrair a f_initial_num_containers_per_ship_trip o valor do get_num_containers_per_cargoManifest do unloading_cargo_id

return (f_initial_num_containers_per_ship_trip);
exception
when no_data_found then
return 0;
end;