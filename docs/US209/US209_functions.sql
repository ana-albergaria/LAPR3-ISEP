--GET_CARGO_MANIFEST_BY_MMSI_AND_DATE
--CARGO MANIFEST STORE
create or replace function get_cargo_manifest_by_mmsi_and_date(f_mmsi shipTrip.mmsi%type, f_date shipTrip.est_departure_date%type) return cargoManifest.cargoManifest_id%type
is
f_shiptrip_id shipTrip.shiptrip_id%type;
f_cargoManifest_id cargoManifest.cargoManifest_id%type;
begin

--tenho de ver qual o loading cargo manifest imediatamente anterior a um determinado momento
select loading_cargo_id into f_cargoManifest_id
from
    (select loading_cargo_id from shipTrip
     where mmsi=f_mmsi AND est_departure_date<=f_date
     order by est_departure_date desc)
where rownum=1;

--tenho de ir buscar a shipTrip desse f_temp_cargoManifest_id
select shiptrip_id into f_shiptrip_id
from shipTrip
where loading_cargo_id=f_cargoManifest_id;

--tenho de ver se poderá estar após o unloading
select unloading_cargo_id into f_cargoManifest_id
from shipTrip
where shiptrip_id=f_shiptrip_id AND f_date>est_arrival_date;

return (f_cargoManifest_id);
exception
when no_data_found then
return -1;
end;