ALTER TABLE reservation
    ADD COLUMN client_cpf VARCHAR(11) NOT NULL UNIQUE,
    ADD CONSTRAINT fk_reservation_client FOREIGN KEY (client_cpf) REFERENCES client(cpf);
