CREATE TABLE reservation_payment(
    id SERIAL PRIMARY KEY,
    client_id INTEGER,
    client_cpf VARCHAR(11),
    r_number NUMERIC (3),
    r_id INTEGER,
    p_id INTEGER,
    p_booking SERIAL,
    p_transaction VARCHAR(100),

    FOREIGN KEY (client_id,client_cpf) REFERENCES client (id,cpf),
    FOREIGN KEY (r_id,r_number) REFERENCES room (id,room_number),
    FOREIGN KEY (p_id,p_booking,p_transaction) REFERENCES payments (id, reservation_id, transaction_id)
);