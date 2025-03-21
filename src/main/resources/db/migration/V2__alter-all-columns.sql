ALTER TABLE client
    ALTER COLUMN name SET NOT NULL,
    ALTER COLUMN name TYPE VARCHAR(60),
    ALTER COLUMN cpf SET NOT NULL,
    ALTER COLUMN cpf TYPE VARCHAR(60),
    ALTER COLUMN email SET NOT NULL,
    ALTER COLUMN email TYPE VARCHAR(60),
    ALTER COLUMN cellphone SET NOT NULL,
    ALTER COLUMN cellphone TYPE VARCHAR(60);

ALTER TABLE client ADD CONSTRAINT client_name_unique UNIQUE (name);
ALTER TABLE client ADD CONSTRAINT client_cpf_unique UNIQUE (cpf);
ALTER TABLE client ADD CONSTRAINT client_email_unique UNIQUE (email);
ALTER TABLE client ADD CONSTRAINT client_cellphone_unique UNIQUE (cellphone);