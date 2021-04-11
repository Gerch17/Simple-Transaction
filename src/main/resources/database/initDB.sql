CREATE TABLE IF NOT EXISTS  users
(
    id BIGSERIAL PRIMARY KEY ,
    user_name VARCHAR NOT NULL ,
    password VARCHAR NOT NULL ,
    user_role VARCHAR NOT NULL ,
    balance INT NOT NULL ,
    is_blocked BOOLEAN NOT NULL ,
    attempts INT NOT NULL
);

CREATE TABLE IF NOT EXISTS history
(
    id BIGSERIAL PRIMARY KEY ,
    user_name VARCHAR NOT NULL ,
    amount INT NOT NULL ,
    transaction_date DATE NOT NULL
);
