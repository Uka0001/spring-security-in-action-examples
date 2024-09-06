CREATE DATABASE baseauth;

\c baseauth

CREATE TABLE IF NOT EXISTS users (
                                     id SERIAL PRIMARY KEY,
                                     username VARCHAR(45) NOT NULL,
                                     email VARCHAR(45) NOT NULL,
                                     password VARCHAR(45) NOT NULL,
                                     enabled INT NOT NULL
);

CREATE TABLE IF NOT EXISTS authorities (
                                           id SERIAL PRIMARY KEY,
                                           username VARCHAR(45) NOT NULL,
                                           authority VARCHAR(45) NOT NULL
);

INSERT INTO authorities (id, username, authority) VALUES (DEFAULT, 'john', 'write') ON CONFLICT DO NOTHING;
INSERT INTO users (id, username, email, password, enabled) VALUES (DEFAULT, 'john', 'john@example.com', '12345', 1) ON CONFLICT DO NOTHING;