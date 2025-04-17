CREATE TABLE payments (
    id SERIAL PRIMARY KEY,
    reservation_id SERIAL,
    reservation_number NUMERIC(3),
    amount DECIMAL(10,2) NOT NULL,
    currency VARCHAR(10) NOT NULL,
    payment_status VARCHAR(20) NOT NULL DEFAULT 'PENDING',
    payment_method VARCHAR(50) NOT NULL,
    transaction_id VARCHAR(100) UNIQUE,
    created_at TIMESTAMP DEFAULT NOW(),
    updated_at TIMESTAMP DEFAULT NOW(),
    FOREIGN KEY (reservation_id,reservation_number) REFERENCES reservation(id,number) ON DELETE CASCADE
);