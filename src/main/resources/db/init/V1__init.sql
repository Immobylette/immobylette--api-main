CREATE EXTENSION IF NOT EXISTS hstore;

-- Function to calculate the distance between two points on the Earth's surface
-- The result is in meters
CREATE OR REPLACE FUNCTION calculate_distance(
    lat1  double precision,
    lon1  double precision,
    lat2  double precision,
    lon2 double precision
)
    RETURNS NUMERIC AS $$
DECLARE
    R NUMERIC := 6371 * 1000;
    phi1 NUMERIC := RADIANS(lat1);
    phi2 NUMERIC := RADIANS(lat2);
    delta_phi NUMERIC := RADIANS(lat2 - lat1);
    delta_lambda NUMERIC := RADIANS(lon2 - lon1);
    a NUMERIC;
    c NUMERIC;
    distance NUMERIC;
BEGIN
    a := SIN(delta_phi / 2) ^ 2 + COS(phi1) * COS(phi2) * SIN(delta_lambda / 2) ^ 2;
    c := 2 * ATAN2(SQRT(a), SQRT(1 - a));
    distance := R * c;
    RETURN distance;
END;
$$ LANGUAGE plpgsql;

-- Function to calculate the number of steps in a room for a given inventory
CREATE OR REPLACE FUNCTION calculate_nb_steps(
    room_id  uuid,
    inventory_id  uuid
)
    RETURNS INT AS $$
DECLARE
    nb INT;
BEGIN
    SELECT COUNT(s.id) INTO nb
    FROM rooms r
             LEFT JOIN elements e ON e.fk_room = r.id
             LEFT JOIN steps s ON s.fk_element = e.id
    WHERE r.id = room_id
      AND s.fk_inventory = inventory_id;
    RETURN nb;
END;
$$ LANGUAGE plpgsql;

-- Function to calculate the number of elements in a room
CREATE OR REPLACE FUNCTION calculate_nb_elements(
    room_id  uuid
)
    RETURNS INT AS $$
DECLARE
    nb INT;
BEGIN
    SELECT COUNT(e.id) INTO nb
    FROM rooms r
             LEFT JOIN elements e ON e.fk_room = r.id
    WHERE r.id = room_id;
    RETURN nb;
END;
$$ LANGUAGE plpgsql