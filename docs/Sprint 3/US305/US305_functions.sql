--get_route_id
create or replace function get_route_id(f_container_id container.container_id%type, f_registration_code client.registration_code%type) return varchar
is
    container_exists integer;
    not_leased_client integer;
    f_route_id route.route_id%type;

    ex_invalid_container_id exception;
    ex_not_leased_client exception;
begin

    select count(*) into container_exists from container where container_id=f_container_id;

    if container_exists = 0 then
        raise ex_invalid_container_id;
    end if;

    select count(*) into not_leased_client from shipment where container_id=f_container_id AND registration_code=f_registration_code;

    if not_leased_client = 0 then
        raise ex_not_leased_client;
    end if;

    select route_id into f_route_id from shipment where container_id=f_container_id AND registration_code=f_registration_code;

    return f_route_id;

exception
    when ex_invalid_container_id then
        return '10 – invalid container id';
    when ex_not_leased_client then
        return '11 – container is not leased by client';
    when others then
        return 'Invalid data';
end;

--TESTS

--the container id is invalid, therefore raises ex_invalid_container_id exception
SET SERVEROUTPUT ON;
begin
    dbms_output.put_line('The route id is: '||get_route_id(12,2));
end;

--the container id is valid, but it was not leased by client, therefore raises ex_not_leased_client exception
SET SERVEROUTPUT ON;
begin
    dbms_output.put_line('The route id is: '||get_route_id(9803333,2));
end;

--the container id is valid AND leased by the client, therefore it doesn't raise exception and returns route id
SET SERVEROUTPUT ON;
begin
    dbms_output.put_line('The route id is: '||get_route_id(9803333,6));
end;