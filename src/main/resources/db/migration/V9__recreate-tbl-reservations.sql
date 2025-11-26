CREATE TABLE reservation (
    id SERIAL PRIMARY KEY,
    client_id INT NOT NULL,
    room_id INT NOT NULL,
    room_number INT NOT NULL,
    FOREIGN KEY (client_id) REFERENCES clients(id),
    FOREIGN KEY (room_id) REFERENCES rooms(id)
);