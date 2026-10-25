CREATE TABLE account_entries (
    id BIGSERIAL PRIMARY KEY,
    transaction_id BIGINT NOT NULL
        REFERENCES transactions(id),
    account_id BIGINT NOT NULL
        REFERENCES account(id),
    amount NUMERIC(19, 2) NOT NULL
);