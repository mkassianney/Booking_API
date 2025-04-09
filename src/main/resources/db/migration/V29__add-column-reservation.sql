ALTER TABLE reservation
    ADD COLUMN room_type VARCHAR (6),
    ADD COLUMN price_per_night NUMERIC (5,2);