CREATE TABLE account (
    id BIGSERIAL PRIMARY KEY,
    account_number VARCHAR(20) NOT NULL UNIQUE,
    balance NUMERIC(19, 2) NOT NULL,
    status VARCHAR(20) NOT NULL,
    customer_id BIGINT NOT NULL,

    CONSTRAINT fk_account_customer
        FOREIGN KEY (customer_id)
        REFERENCES customer(id)
);