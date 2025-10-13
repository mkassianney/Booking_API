CREATE TABLE reservations (
    id SERIAL PRIMARY KEY,
    check_in TIMESTAMP NOT NULL,
    check_out TIMESTAMP NOT NULL,
    client_id INT NOT NULL REFERENCES clients(id),
    client_name VARCHAR(150) NOT NULL,
    client_cpf CHAR(11) NOT NULL,
    client_email VARCHAR(150) NOT NULL,
    room_id INT NOT NULL REFERENCES rooms(id),
    room_type VARCHAR(20) NOT NULL,
    room_number SMALLINT NOT NULL,
    price NUMERIC(7,2) NOT NULL
);