CREATE TABLE product (
    id SERIAL PRIMARY KEY,
    name VARCHAR NOT NULL,
    description VARCHAR,
    color VARCHAR,
    brand VARCHAR NOT NULL,
    flavor VARCHAR,
    sensation VARCHAR,
    category VARCHAR,
    sub_category VARCHAR,
    target_audience VARCHAR,
    size VARCHAR,
    fabric VARCHAR,
    additional_information VARCHAR,
    quantity BIGINT,
    stock_quantity BIGINT NOT NULL,
    cost_price NUMERIC(19, 2) NOT NULL,
    sale_price NUMERIC(19, 2) NOT NULL,

    supplier_id BIGINT,

    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_supplier FOREIGN KEY(supplier_id) REFERENCES supplier(id)
);