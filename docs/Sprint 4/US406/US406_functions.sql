--////////////////////////////////////////////////////// BEGINNING: US405

--GET SHIP MAX CAPACITY

create or replace function get_ship_max_capacity(f_mmsi ship.mmsi%type) return integer
is

    f_max_capacity integer;

begin

    select to_number(currentCapacity) into f_max_capacity
        from ship
            where mmsi = f_mmsi;

return (f_max_capacity);

exception
        when no_data_found then
        return 0;

end;
/

--//////////////////////////////////////////////////////

--GET NUMBER OF CONTAINER IN A SHIP ON A DAY

create or replace function get_num_containers_ship_day(f_mmsi ship.mmsi%type, f_date shipTrip.real_departure_date%type) return integer
is

    f_current_num integer:=0;
    f_shiptrip_id shipTrip.shiptrip_id%type;
    f_loading_cargo_id shipTrip.loading_cargo_id%type;
    f_unloading_cargo_id shipTrip.unloading_cargo_id%type;

cursor loadingCargos
        is
        (select loading_cargo_id
        from shipTrip
        where mmsi=f_mmsi AND real_departure_date < f_date);

cursor unloadingCargos
        is
        (select unloading_cargo_id
        from shipTrip
        where mmsi=f_mmsi AND real_arrival_date < f_date);

begin

    open loadingCargos;
    loop
    fetch loadingCargos into f_loading_cargo_id;
        exit when loadingCargos%notfound;
        f_current_num := f_current_num + get_num_containers_per_cargoManifest(f_loading_cargo_id);
    end loop;

    open unloadingCargos;
    loop
    fetch unloadingCargos into f_unloading_cargo_id;
        exit when unloadingCargos%notfound;
        f_current_num := f_current_num + get_num_containers_per_cargoManifest(f_unloading_cargo_id);
    end loop;

return f_current_num;

exception
        when no_data_found then
        return 0;

end;
/

--////////////////////////////////////////////////////// END: US405

--////////////////////////////////////////////////////// BEGINNING: US406

create or replace FUNCTION get_all_voyages RETURN SYS_REFCURSOR
as
    below_threshold SYS_REFCURSOR;


BEGIN
    OPEN below_threshold FOR
    SELECT mmsi, shiptrip_id, departure_location, arrival_location, real_departure_date, real_arrival_date
        FROM SHIPTRIP WHERE real_arrival_date IS NOT NULL;

RETURN (below_threshold);

EXCEPTION
    WHEN no_data_found THEN
        RETURN NULL;
END;
/--////////////////////////////////////////////////////// END: US406
