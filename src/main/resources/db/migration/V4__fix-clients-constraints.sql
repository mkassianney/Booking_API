ALTER TABLE client
    ALTER COLUMN name SET NOT NULL,
    ALTER COLUMN email SET NOT NULL,
    ALTER COLUMN cpf SET NOT NULL,
    ALTER COLUMN cellphone SET NOT NULL;

ALTER TABLE client
    ADD CONSTRAINT unique_email UNIQUE (email),
    ADD CONSTRAINT unique_cpf UNIQUE (cpf),
    ADD CONSTRAINT unique_cellphone UNIQUE (cellphone);
