CREATE TABLE reservation (

	id SERIAL PRIMARY KEY,
	checkIn date NOT NULL,
	checkOut date NOT NULL,
	roomNumber NUMERIC(3),
	roomType VARCHAR(6) NOT NULL,
	pricePerNight NUMERIC(5,2) NOT NULL
);