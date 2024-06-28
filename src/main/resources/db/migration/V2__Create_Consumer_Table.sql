CREATE TABLE consumer (
    id SERIAL PRIMARY KEY,

    name VARCHAR NOT NULL,
    gender VARCHAR CHECK (
        gender IN (
            'MAN', 'CIS_MAN', 'INTERSEX_MAN', 'TRANS_MAN', 'TRANSMASCULINE',
            'WOMAN', 'CIS_WOMAN', 'INTERSEX_WOMAN', 'TRANS_WOMAN',
            'TRANS_FEMININE', 'BEYONDE_BINARY', 'AGENDER', 'BIGENDER',
            'GENDERFLUID', 'GENDER_QUESTIONING', 'GENDERQUEER', 'INTERSEX',
            'NONBINARY', 'PANGENDER', 'TRANS_PERSON', 'TWO_SPIRIT', 'NOT_LISTED'
        )
    ),
    sexual_orientation VARCHAR CHECK (
        sexual_orientation IN (
            'STRAIGHT', 'GAY', 'LESBIAN', 'BISEXUAL', 'ASEXUAL', 'DEMISEXUAL',
            'PANSEXUAL', 'QUEER', 'QUESTIONING', 'AROMANTIC', 'OMINISEXUAL',
            'NOT_LISTED'
        )
    ),
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

