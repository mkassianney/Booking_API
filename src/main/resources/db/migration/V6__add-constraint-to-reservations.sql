ALTER TABLE reservations
ADD CONSTRAINT fk_reservation_client
FOREIGN KEY (client_id) REFERENCES clients(id);
