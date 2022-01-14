--function to check if the ship has been idle on a given day
CREATE OR REPLACE FUNCTION check_ship_idle_day(f_ship_mmsi ship.mmsi%type, f_day_date date) return boolean
IS
   c_real_departure_date date;
   c_real_arrival_date date;
   c_shiptrip_id shiptrip.shiptrip_id%type;

    CURSOR c_trips is
      SELECT shiptrip_id, real_departure_date, real_arrival_date FROM SHIPTRIP WHERE MMSI=f_ship_mmsi;

BEGIN
    OPEN c_trips;
    LOOP
    FETCH c_trips into c_shiptrip_id, c_real_departure_date, c_real_arrival_date;
        EXIT WHEN c_trips%notfound;
        --if the ship had at least a shiptrip where it has left OR arrived in that date, it means the ship was not idle in that day
        IF (c_real_departure_date = f_day_date OR c_real_arrival_date = f_day_date) THEN
            return false;
        END IF;

    END LOOP;
    CLOSE c_trips;

    return true;

END;
/

--function to count the days a ship has been idle since the beginning of the year
CREATE OR REPLACE FUNCTION check_ship_idle_year(f_ship_mmsi ship.mmsi%type, f_start_year date) return integer
IS
    count_days integer := 0;
    current_date date := f_start_year;
    isIdleOnDay boolean;

BEGIN
    WHILE to_char(current_date, 'DD-MM-YYYY') != to_char(sysdate+1, 'DD-MM-YYYY')
    LOOP
        isIdleOnDay := check_ship_idle_day(f_ship_mmsi, current_date);
        IF isIdleOnDay THEN
            count_days := count_days+1;
        END IF;
        current_date := current_date+1;
    END LOOP;

    return count_days;
END;
/