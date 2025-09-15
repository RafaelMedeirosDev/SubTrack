CREATE TABLE IF NOT EXISTS users (
    id uuid PRIMARY KEY DEFAULT gen_random_uuid(),
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS subscriptions (
    id uuid PRIMARY KEY DEFAULT gen_random_uuid(),
    plataforma_name VARCHAR(100) NOT NULL,
    user_id uuid REFERENCES users(id),
    value DECIMAL(10, 2) NOT NULL,
    billing_date DATE NOT NULL
);