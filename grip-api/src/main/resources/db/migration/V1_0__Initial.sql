CREATE TABLE IF NOT EXISTS account (
    id serial PRIMARY KEY,
    created_at timestamp,
    updated_at timestamp
);

CREATE TABLE IF NOT EXISTS user_account (
    id UUID PRIMARY KEY,
    role VARCHAR(256),
    created_at timestamp,
    updated_at timestamp,
    account_id INTEGER NOT NULL,
    FOREIGN KEY (account_id) REFERENCES account (id) ON DELETE CASCADE
);
