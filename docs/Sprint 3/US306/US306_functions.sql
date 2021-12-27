create or replace procedure begin_US306
IS
begin
EXECUTE IMMEDIATE 'create table tempCont(
                                            container_id integer NOT NULL,
                                            temperature_kept numeric(6,2),
                                            gross numeric(5,2) NOT NULL,
                                            constraint fk_tempCont_container FOREIGN KEY (container_id) references container(container_id),
                                            constraint chk_gross check(gross >= 0)
                   )';
end begin_US306;
/

create or replace function put_desired_containers_in_table(f_warehouse_id warehouse.warehouse_id%type,f_currentDate truckTrip.est_departure_date%type, f_finalDate truckTrip.est_departure_date%type) return integer
is
f_container_id containerInCargoManifest.container_id%type;
f_temperature_kept containerInCargoManifest.temperature_kept%type;
f_gross container.gross%type;
f_warehouse_location warehouse.location_id%type;
cursor desiredContainers
is
(select container_id
from containerInCargoManifest
where cargomanifest_id = (select loading_cargo_id from trucktrip where departure_location = f_warehouse_location) AND
(select est_departure_date from truckTrip where departure_location = f_warehouse_location) > f_currentDate AND
(select est_departure_date from truckTrip where departure_location = f_warehouse_location) <= f_finalDate);
begin
begin_us306;
select location_id into f_warehouse_location from warehouse where warehouse_id=f_warehouse_id;
open desiredContainers;
loop
fetch desiredContainers into f_container_id;
exit when desiredContainers%notfound;
select temperature_kept into f_temperature_kept
from containerInCargoManifest
where container_id = f_container_id;
select gross into f_gross
from container
where container_id = f_container_id;
insert into tempCont (container_id, temperature_kept, gross) values (f_container_id, f_temperature_kept, f_gross);
end loop;
return 1;
exception
when no_data_found then
return 0;
end;
/

create or replace procedure end_US306
IS
begin
execute immediate 'drop table tempCont';
exception
WHEN OTHERS THEN null;
end end_US306;
/

--CHECK IF WAREHOUSE EXISTS
create or replace function check_if_warehouse_exists(f_warehouse_id warehouse.warehouse_id%type) return integer
is
f_result integer;
begin
select count(*) into f_result
from warehouse
where warehouse_id = f_warehouse_id;
return (f_result);
exception
when no_data_found then
return 0;
end;
/

--GET CURRENT CONTAINERS WAREHOUSE
create or replace function get_current_containers_warehouse(f_warehouse_id warehouse.warehouse_id%type, f_current_date shipTrip.est_departure_date%type) return integer
is
f_comp_cargo_id cargoManifest.cargoManifest_id%type;
f_current_containers integer:=0;
cursor neededCargosPut
is
(select unloading_cargo_id
from truckTrip
where arrival_location=(select location_id from warehouse where warehouse_id=f_warehouse_id) AND est_arrival_date < f_current_date);
cursor neededCargosTake
is
(select loading_cargo_id
from truckTrip
where departure_location=(select location_id from warehouse where warehouse_id=f_warehouse_id) AND est_departure_date < f_current_date);
begin
open neededCargosPut;
loop
fetch neededCargosPut into f_comp_cargo_id;
exit when neededCargosPut%notfound;
f_current_containers:= f_current_containers + get_num_containers_per_cargoManifest(f_comp_cargo_id);
end loop;
open neededCargosTake;
loop
fetch neededCargosTake into f_comp_cargo_id;
exit when neededCargosTake%notfound;
f_current_containers:= f_current_containers - get_num_containers_per_cargoManifest(f_comp_cargo_id);
end loop;
return f_current_containers;
exception
when no_data_found then
return 0;
end;
/

--GET WAREHOUSE MAX CAPACITY
create or replace function get_warehouse_max_capacity(f_warehouse_id warehouse.warehouse_id%type) return integer
is
f_max_capacity integer;
begin
select maxCapacity into f_max_capacity
from warehouse
where warehouse_id = f_warehouse_id;
return (f_max_capacity);
exception
when no_data_found then
return 0;
end;
/

------------------------------------------------------------REUSED------------------------------------------------------------
--GET NUM CONTAINERS PER CARGO MANIFEST
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
/