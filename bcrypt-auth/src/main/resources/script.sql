CREATE TABLE IF NOT EXISTS "user" (
                                      id SERIAL PRIMARY KEY,
                                      username VARCHAR(45) NOT NULL,
                                      password TEXT NOT NULL,
                                      encryption_algorithm VARCHAR(45) NOT NULL
);

CREATE TABLE IF NOT EXISTS "authority" (
                                           id SERIAL PRIMARY KEY,
                                           name VARCHAR(45) NOT NULL,
                                           "user" INT NOT NULL
);

CREATE TABLE IF NOT EXISTS "product" (
                                         id SERIAL PRIMARY KEY,
                                         name VARCHAR(45) NOT NULL,
                                         price VARCHAR(45) NOT NULL,
                                         currency VARCHAR(45) NOT NULL
);

INSERT INTO "user" (id, username, password, encryption_algorithm) VALUES (1, 'john', '$2a$10$PVEL1N7UjuNTn1R41Pan6.I1xBlkCCO9Ov0LC7B0JKd8sNE8F65D.', 'BCRYPT')
ON CONFLICT (id) DO NOTHING;

INSERT INTO "authority" (id, name, "user") VALUES (1, 'READ', 1)
ON CONFLICT (id) DO NOTHING;

INSERT INTO "authority" (id, name, "user") VALUES (2, 'WRITE', 1)
ON CONFLICT (id) DO NOTHING;

INSERT INTO "product" (id, name, price, currency) VALUES (1, 'Chocolate', '10', 'USD')
ON CONFLICT (id) DO NOTHING;