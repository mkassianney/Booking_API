CREATE TABLE reservation (

	id SERIAL,
	check_in date NOT NULL,
	check_out date NOT NULL,
	number NUMERIC(3),
	type VARCHAR(6) NOT NULL,
	price NUMERIC(5,2) NOT NULL,

    PRIMARY KEY (id,number)
);