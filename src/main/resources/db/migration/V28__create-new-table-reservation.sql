CREATE TABLE reservation (

	id SERIAL PRIMARY KEY,
	checkIn date NOT NULL,
	checkOut date NOT NULL,
	room_n NUMERIC(3),

    FOREIGN KEY (room_n)
        REFERENCES room (room_number)
);