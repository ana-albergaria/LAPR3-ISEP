--CHECK PORT OCCUPANCY FOR A GIVEN DAY



--GET PORT MAX CAPACITY

create or replace function get_port_max_capacity(f_port_id port.port_id%type) return integer
is
f_max_capacity integer;
begin
select maxCapacity into f_max_capacity
from port
where port_id = f_port_id;
return (f_max_capacity);
exception
when no_data_found then
return 0;
end;


--CHECK IF PORT EXISTS

create or replace function check_if_port_exists(f_port_id port.port_id%type) return integer
is
f_result integer;
begin
select count(*) into f_result
from port
where port_id = f_port_id;
return (f_result);
exception
when no_data_found then
return 0;
end;
/