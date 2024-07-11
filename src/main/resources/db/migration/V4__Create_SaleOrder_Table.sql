CREATE TABLE sale_order (
    id SERIAL PRIMARY KEY,
    consumer_id BIGINT,
    total NUMERIC(19, 2) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_sale_order_consumer FOREIGN KEY(consumer_id) REFERENCES consumer(id)
);
