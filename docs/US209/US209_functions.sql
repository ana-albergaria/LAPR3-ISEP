--GET_CARGO_MANIFEST_BY_MMSI_AND_DATE
create or replace function get_cargo_manifest_by_mmsi_and_date(f_mmsi shipTrip.mmsi%type, f_date shipTrip.est_departure_date%type) return cargoManifest.cargoManifest_id%type
is
f_cargoManifest_id cargoManifest.cargoManifest_id%type;
begin

select loading_manifest_id into f_cargoManifest_id
from shipTrip
where est_departure_date<=f_date AND est_arrival_date>0;
--select unloading_manifest_id into f_cargoManifest_id
--from shipTrip
--where est_arrival_date>=0 AND ; --AND est_departure_date DA SHIP TRIP QUE OCORREU DEPOIS DESSA

--E ISTO DEVIA SER SE AINDA NAO ACONTECEU, CASO CONTRARIO DEVIAM SER USADOS OS REAL E NAO OS EST

return (f_cargoManifest_id);
exception
when no_data_found then
return -1;
end;