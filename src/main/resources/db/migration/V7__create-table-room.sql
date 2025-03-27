CREATE TABLE room (

	id SERIAL PRIMARY KEY,
	roomNumber NUMERIC(3),
	roomType VARCHAR(6) NOT NULL,
	pricePerNight NUMERIC(5,2) NOT NULL,
	description VARCHAR(150) NOT NULL,
	available BOOLEAN NOT NULL

);