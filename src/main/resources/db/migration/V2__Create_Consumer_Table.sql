CREATE TABLE consumer (
    id SERIAL PRIMARY KEY,

    name VARCHAR NOT NULL,
    gender VARCHAR,
    sexual_orientation VARCHAR,
    date_of_birth DATE,
    phone_number VARCHAR UNIQUE NOT NULL,
    email VARCHAR UNIQUE NOT NULL,
    cpf VARCHAR UNIQUE NOT NULL,

    address_cep VARCHAR,
    address_public_place VARCHAR,
    address_number VARCHAR,
    address_complement VARCHAR,
    address_district VARCHAR,
    address_city VARCHAR,
    address_state VARCHAR,

    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

