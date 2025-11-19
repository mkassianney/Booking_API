CREATE TABLE rooms (
    id SERIAL PRIMARY KEY,
    room_number SMALLINT UNIQUE NOT NULL,
    room_type VARCHAR(20) NOT NULL,
    price_per_night NUMERIC(7,2) NOT NULL,
    description VARCHAR(255) NOT NULL,
    available BOOLEAN NOT NULL DEFAULT TRUE
);